package gwangjang.server.domain.auth.application.service;

import gwangjang.server.domain.auth.application.dto.request.LocalSignUpRequest;
import gwangjang.server.domain.auth.application.dto.request.SignUpRequest;
import gwangjang.server.domain.auth.application.dto.response.SignInResponse;
import gwangjang.server.domain.auth.application.mapper.MemberMapper;
import gwangjang.server.domain.auth.exception.EmailDuplicationException;
import gwangjang.server.domain.auth.exception.NicknameDuplicationException;
import gwangjang.server.domain.member.domain.service.MemberSaveService;
import gwangjang.server.global.security.jwt.service.TokenUtil;
import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.member.domain.entity.constant.RegistrationStatus;
import gwangjang.server.domain.member.domain.service.MemberCheckService;
import gwangjang.server.domain.member.domain.service.MemberQueryService;
import gwangjang.server.global.response.TokenInfoResponse;
import gwangjang.server.global.utils.AuthenticationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class SignUpUserCase {

    private final TokenUtil tokenUtil;
    private final MemberQueryService memberQueryService;
    private final MemberSaveService memberSaveService;
    private final MemberCheckService memberCheckService;
    private final MemberMapper memberMapper;

    // 추가 정보 기입
    public SignInResponse signUp(String token, SignUpRequest signUpRequest) {

       //1. 유저 찾기 -> 소셜 로그인 || 로컬 로그인 모두 이미 member 객체 있음
        String socialId = tokenUtil.getSocialId(token);
        Member member = memberQueryService.getMemberBySocialId(socialId);
        //2. signUp 처리
        String nickName=signUpRequest.getNickname();
        if(memberCheckService.checkNickname(nickName)) throw new NicknameDuplicationException(); //닉네임 중복검사 (이중체크)
        member.signUp(signUpRequest);
        //3. security 처리
        AuthenticationUtil.makeAuthentication(member);
        //4. token 만들기
        TokenInfoResponse tokenResponse = tokenUtil.createToken(member.getSocialId(),
                member.getRegistrationStatus().equals(RegistrationStatus.COMPLETED),member.getRole().getKey());
//        //5. refresh token 저장
        tokenUtil.storeRefreshToken(member.getSocialId(), tokenResponse);
//
        return SignInResponse.from(tokenResponse, member.getRegistrationStatus());
    }
    // 추가 정보 기입
    public SignInResponse localSignUp(LocalSignUpRequest localSignUpRequest) {


        //2. signUp 처리
        String nickname=localSignUpRequest.getNickname();
        if(memberCheckService.checkNickname(nickname)) throw new NicknameDuplicationException(); //닉네임 중복검사 (이중체크)

        String email = localSignUpRequest.getEmail();
        if(memberCheckService.checkEmail(email)) throw new EmailDuplicationException();

        Member member = memberSaveService.saveMember(memberMapper.createLocalMember(localSignUpRequest));
        //3. security 처리
        AuthenticationUtil.makeAuthentication(member);
        //4. token 만들기
        TokenInfoResponse tokenResponse = tokenUtil.createToken(member.getSocialId(),
                member.getRegistrationStatus().equals(RegistrationStatus.COMPLETED),member.getRole().getKey());
//        //5. refresh token 저장
        tokenUtil.storeRefreshToken(member.getSocialId(), tokenResponse);
//
        return SignInResponse.from(tokenResponse, member.getRegistrationStatus());
    }


}
