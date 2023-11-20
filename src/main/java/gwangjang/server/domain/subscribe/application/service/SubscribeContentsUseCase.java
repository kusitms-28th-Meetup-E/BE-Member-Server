package gwangjang.server.domain.subscribe.application.service;

import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.member.domain.service.MemberQueryService;
import gwangjang.server.domain.subscribe.application.mapper.SubscribeMapper;
import gwangjang.server.domain.subscribe.domain.service.SubscribeQueryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscribeContentsUseCase {

    private final MemberQueryService memberQueryService;

    private final SubscribeQueryService subscribeQueryService;
    private final SubscribeMapper subscribeMapper = new SubscribeMapper();

    public List<Long> getMySubscribeIssueList(String memberId) {
        Member member = memberQueryService.getMemberBySocialId(memberId);
        return subscribeQueryService.getMySubscribeList(member);
    }
}
