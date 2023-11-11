package gwangjang.server.domain.auth.application.mapper;

import gwangjang.server.domain.auth.application.dto.request.LocalSignUpRequest;
import gwangjang.server.domain.auth.application.dto.response.GoogleUserResponse;
import gwangjang.server.domain.auth.application.dto.response.KakaoUserResponse;
import gwangjang.server.domain.member.adapter.consumer.web.dto.post.MemberDto;
import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.member.domain.entity.constant.RegistrationStatus;
import gwangjang.server.domain.member.domain.entity.constant.Role;
import gwangjang.server.domain.member.domain.entity.constant.SocialProvider;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MemberMapper {

    public Member createKakaoMember(KakaoUserResponse kakaoUserResponse) {
        Member newMember = Member.builder()
                .socialId(SocialProvider.KAKAO + "-" + kakaoUserResponse.getId())
                .provider(SocialProvider.KAKAO)
                .email(kakaoUserResponse.getKakaoAccount().getEmail())
                .role(Role.USER)
                .registrationStatus(RegistrationStatus.UNCOMPLETED)
                .build();

        return newMember;
    }

    public Member createLocalMember(LocalSignUpRequest localSignUpRequest) {
        return Member.builder()
                .socialId(SocialProvider.LOCAL + "-" + UUID.randomUUID())
                .provider(SocialProvider.LOCAL)
                .email(localSignUpRequest.getEmail())
                .role(Role.USER)
                .registrationStatus(RegistrationStatus.COMPLETED)
                .build();
    }

    public MemberDto mapToMemberDto(Member member) {
        return MemberDto.builder()
                .memberId(member.getSocialId())
                .nickname(member.getNickname())
                .profileImage(member.getProfileImage())
                .build();
    }
}
