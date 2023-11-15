package gwangjang.server.domain.subscribe.domain.service;

import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.subscribe.application.dto.res.SubscribeMemberDto;
import gwangjang.server.domain.subscribe.domain.entity.Subscribe;
import gwangjang.server.domain.subscribe.domain.repository.SubscribeRepository;
import gwangjang.server.global.annotation.DomainService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@DomainService
@RequiredArgsConstructor
public class SubscribeQueryService {

    private final SubscribeRepository subscribeRepository;

    public SubscribeMemberDto subscribeListByMember(Member member) {
        return subscribeRepository.findAllSubscribeByMember(member);
    }

    public Subscribe findSubscribeByMemberAndTopic(Member member, String topic, String issue) {
        return subscribeRepository.findSubscribeByMemberAndTopic(member, topic, issue);
    }

    public boolean isAbleToSubscribe(Member member) {
        return subscribeRepository.findCountSubscribeByMember(member);
    }
}
