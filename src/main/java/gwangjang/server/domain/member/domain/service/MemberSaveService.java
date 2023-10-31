package gwangjang.server.domain.member.domain.service;

import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.member.domain.repository.MemberRepository;
import gwangjang.server.global.annotation.DomainService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.Optional;

@DomainService
@Transactional
@AllArgsConstructor
public class MemberSaveService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        Optional<Member> findMember=memberRepository.findByEmail(member.getEmail());
        if(findMember.isEmpty()){
            return memberRepository.save(member);
        } else {
            return findMember.get();
        }
    }
}
