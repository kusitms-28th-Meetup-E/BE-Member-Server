package gwangjang.server.domain.subscribe.application.service;

import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.member.domain.service.MemberQueryService;
import gwangjang.server.domain.subscribe.application.dto.res.SubscribeRes;
import gwangjang.server.domain.subscribe.application.mapper.SubscribeMapper;
import gwangjang.server.domain.subscribe.domain.entity.Subscribe;
import gwangjang.server.domain.subscribe.domain.service.SubscribeDeleteService;
import gwangjang.server.domain.subscribe.domain.service.SubscribeQueryService;
import gwangjang.server.global.annotation.DomainService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UnSubscribeUseCase {

    private final MemberQueryService memberQueryService;
    private final SubscribeDeleteService subscribeDeleteService;
    private final SubscribeQueryService subscribeQueryService;

    private final SubscribeMapper subscribeMapper = new SubscribeMapper();

    public SubscribeRes unSubscribe(String socialId, Long topicId, Long issueId) {
        Member member = memberQueryService.getMemberBySocialId(socialId);
        Subscribe subscribe = subscribeQueryService.findSubscribeByMemberAndTopic(member, topicId,issueId);
        subscribeDeleteService.delete(subscribe);

        return subscribeMapper.mapToSubscribeRes(subscribe).setUnScribe();
    }
}
