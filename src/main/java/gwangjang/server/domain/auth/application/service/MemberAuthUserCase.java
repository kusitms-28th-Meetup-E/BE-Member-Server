package gwangjang.server.domain.auth.application.service;

import gwangjang.server.domain.auth.application.dto.request.SignUpRequest;
import gwangjang.server.domain.auth.exception.AccountAlreadyExistedException;
import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.member.domain.service.MemberSaveService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberAuthUserCase {

    private final MemberSaveService memberSaveService;

    @Transactional
    public Member auth(Member member, String providerInfo) {
        Member signInMember = memberSaveService.saveMember(member);
        checkRegistration(signInMember, providerInfo);
        return signInMember;
    }


    // 다른 플랫폼으로 가입했으면 에러 출력
    private void checkRegistration(Member signInMember, String providerInfo) {
        if(!providerInfo.contains((signInMember.getProvider().name().toLowerCase())))
            throw new AccountAlreadyExistedException();
    }

}
