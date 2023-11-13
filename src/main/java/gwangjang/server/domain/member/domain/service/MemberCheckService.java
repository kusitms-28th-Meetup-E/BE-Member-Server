package gwangjang.server.domain.member.domain.service;

import gwangjang.server.domain.member.domain.repository.MemberRepository;
import gwangjang.server.global.annotation.DomainService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainService
@Transactional
@RequiredArgsConstructor
public class MemberCheckService {
    private final MemberRepository memberRepository;
    public boolean checkNickname(String nickname) {
        return memberRepository.checkNickname(nickname);
    }
    public boolean checkEmail(String email) {
        return memberRepository.checkEmail(email);
    }
    public boolean checkLoginId(String loginId) {
        return memberRepository.checkLoginId(loginId);
    }

}
