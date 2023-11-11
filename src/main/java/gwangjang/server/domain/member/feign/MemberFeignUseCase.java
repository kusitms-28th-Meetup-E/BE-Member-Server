package gwangjang.server.domain.member.feign;

import gwangjang.server.domain.auth.application.mapper.MemberMapper;
import gwangjang.server.domain.member.adapter.consumer.web.dto.post.MemberDto;
import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberFeignUseCase {

    private final MemberQueryService memberQueryService;
    private final MemberMapper memberMapper = new MemberMapper();


    public MemberDto getMemberDto(String socialId) {
        Member member = memberQueryService.getMemberBySocialId(socialId);
        return memberMapper.mapToMemberDto(member);
    }
}
