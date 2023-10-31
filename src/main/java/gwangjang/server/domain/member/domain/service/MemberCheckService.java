package gwangjang.server.domain.member.domain.service;

import gwangjang.server.domain.member.domain.repository.MemberRepository;
import gwangjang.server.global.annotation.DomainService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@DomainService
@Transactional
@RequiredArgsConstructor
public class MemberCheckService {
    private final MemberRepository memberRepository;
    public boolean checkNickname(String nickname) {
        return memberRepository.checkNickname(nickname);
    }

}
