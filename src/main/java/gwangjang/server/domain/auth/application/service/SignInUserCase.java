package gwangjang.server.domain.auth.application.service;

import gwangjang.server.domain.auth.application.dto.request.LocalSignInRequest;
import gwangjang.server.domain.auth.application.dto.response.SignInResponse;
import gwangjang.server.domain.auth.exception.InCorrectPasswordException;
import gwangjang.server.domain.member.domain.entity.constant.SocialProvider;
import gwangjang.server.global.security.jwt.service.TokenUtil;
import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.member.domain.entity.constant.RegistrationStatus;
import gwangjang.server.domain.member.domain.service.MemberGetService;
import gwangjang.server.global.response.TokenInfoResponse;
import gwangjang.server.global.utils.AuthenticationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SignInUserCase {

    private final MemberAuthUserCase internalAuthService;
    private final TokenUtil tokenUtil;
    private final Map<String, SignInProvider> signInProviders;
    private final MemberGetService memberGetService;


    public SignInResponse signIn(String authToken, String providerInfo) {


        // 0. auth -> access Token
        String token = getAccessTokenFromPlatform(authToken, providerInfo);
        log.info("token -> {}", token);

        //1. 사용자 정보 가져오기
        Member member = getUserDataFromPlatform(token, providerInfo);
        //2. 로그인 및 회원가입
        Member authenticatedMember = internalAuthService.auth(member, providerInfo);
        //3. security 처리
        AuthenticationUtil.makeAuthentication(authenticatedMember);
        //4. token 만들기
        TokenInfoResponse tokenResponse = tokenUtil.createToken(authenticatedMember.getSocialId(),
                authenticatedMember.getRegistrationStatus().equals(RegistrationStatus.COMPLETED),authenticatedMember.getRole().getKey());
        //5. refresh token 저장
        tokenUtil.storeRefreshToken(authenticatedMember.getSocialId(), tokenResponse);

        return SignInResponse.from(tokenResponse, authenticatedMember.getRegistrationStatus());
    }

    private Member getUserDataFromPlatform(String accessToken, String providerInfo) {
        SignInProvider signInProvider = signInProviders.get(providerInfo);
        if (signInProvider == null) {
            throw new IllegalArgumentException("Unknown provider: " + providerInfo);
        }
        return signInProvider.getUserData(accessToken);
    }
    private String getAccessTokenFromPlatform(String authToken, String providerInfo) {
        SignInProvider signInProvider = signInProviders.get(providerInfo);
        if (signInProvider == null) {
            throw new IllegalArgumentException("Unknown provider: " + providerInfo);
        }
        return signInProvider.getAccessToken(authToken);
    }

    public SignInResponse testSignIn(String socialId, String providerInfo) {
        log.info("test sign in start ");
        //1. 사용자 정보 가져오기
        Member member = memberGetService.getMemberBySocialId(socialId);
        //2. 로그인
        Member authenticatedMember = internalAuthService.auth(member, providerInfo);
        //3. security 처리
        AuthenticationUtil.makeAuthentication(authenticatedMember);
        //4. token 만들기
        TokenInfoResponse tokenResponse = tokenUtil.createToken(authenticatedMember.getSocialId(),
                authenticatedMember.getRegistrationStatus().equals(RegistrationStatus.COMPLETED),authenticatedMember.getRole().getKey());
        //5. refresh token 저장
        tokenUtil.storeRefreshToken(authenticatedMember.getSocialId(), tokenResponse);

        return SignInResponse.from(tokenResponse, authenticatedMember.getRegistrationStatus());
    }


    public SignInResponse localSignIn(LocalSignInRequest localSignInRequest) {

        Member member = memberGetService.getMemberByLoginId(localSignInRequest.getId());
        if (!member.login(localSignInRequest.getPw())) {
            throw new InCorrectPasswordException();
        }
        Member authenticatedMember = internalAuthService.auth(member, "local");

        //3. security 처리
        AuthenticationUtil.makeAuthentication(authenticatedMember);
        //4. token 만들기
        TokenInfoResponse tokenResponse = tokenUtil.createToken(authenticatedMember.getSocialId(),
                authenticatedMember.getRegistrationStatus().equals(RegistrationStatus.COMPLETED),authenticatedMember.getRole().getKey());
        //5. refresh token 저장
        tokenUtil.storeRefreshToken(authenticatedMember.getSocialId(), tokenResponse);
        return SignInResponse.from(tokenResponse, authenticatedMember.getRegistrationStatus());
    }


}
