package gwangjang.server.domain.member.domain.service;

import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.member.exception.NotFoundByLoginIdException;
import gwangjang.server.domain.member.exception.NotFoundBySocialIdException;
import gwangjang.server.domain.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberGetService {

    private final MemberRepository memberRepository;

    public Member getMemberBySocialId(String socialId) {
        return memberRepository.findBySocialId(socialId).orElseThrow(NotFoundBySocialIdException::new);
    }

    public Member getMemberByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId).orElseThrow(NotFoundByLoginIdException::new);
    }
}
