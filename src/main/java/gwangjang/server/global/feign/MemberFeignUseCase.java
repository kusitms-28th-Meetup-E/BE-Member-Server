package gwangjang.server.global.feign;

import gwangjang.server.domain.auth.application.mapper.MemberMapper;
import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.member.domain.service.MemberQueryService;
import gwangjang.server.domain.subscribe.adapter.producer.web.dto.SubscribeData;
import gwangjang.server.domain.subscribe.domain.service.SubscribeQueryService;
import gwangjang.server.global.feign.dto.response.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberFeignUseCase {

    private final MemberQueryService memberQueryService;
    private final SubscribeQueryService subscribeQueryService;
    private final MemberMapper memberMapper = new MemberMapper();


    public MemberDto getMemberDto(String socialId) {
        Member member = memberQueryService.getMemberBySocialId(socialId);
        return memberMapper.mapToMemberDto(member);
    }
    public List<SubscribeData> getAllIssueBySubscribers() {
        return subscribeQueryService.getIssueBySubscribers();
    }
}
