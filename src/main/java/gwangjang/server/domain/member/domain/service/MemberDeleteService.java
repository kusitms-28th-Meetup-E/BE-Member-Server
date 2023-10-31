package gwangjang.server.domain.member.domain.service;

import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.member.domain.repository.MemberRepository;
import gwangjang.server.global.annotation.DomainService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@DomainService
@Transactional
@RequiredArgsConstructor
public class MemberDeleteService {

    private final MemberRepository memberRepository;

    public void deleteMember(Member member){
        this.memberRepository.delete(member);
    }
}
