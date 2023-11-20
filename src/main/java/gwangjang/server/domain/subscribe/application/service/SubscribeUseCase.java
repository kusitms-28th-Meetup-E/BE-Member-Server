package gwangjang.server.domain.subscribe.application.service;

import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.member.domain.service.MemberQueryService;
import gwangjang.server.domain.subscribe.application.dto.res.SubscribeMemberDto;
import gwangjang.server.domain.subscribe.application.dto.res.SubscribeRes;
import gwangjang.server.domain.subscribe.application.mapper.SubscribeMapper;
import gwangjang.server.domain.subscribe.domain.entity.Subscribe;
import gwangjang.server.domain.subscribe.domain.service.SubscribeQueryService;
import gwangjang.server.domain.subscribe.domain.service.SubscribeSaveService;
import gwangjang.server.domain.subscribe.exception.NoAccessSubscribe;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscribeUseCase {

    private final SubscribeSaveService subscribeSaveService;
    private final SubscribeQueryService subscribeQueryService;
    private final MemberQueryService memberQueryService;

    private final SubscribeMapper subscribeMapper = new SubscribeMapper();

    public SubscribeRes subscribe(String socialId,Long topicId, Long issueId) {
        Member member = memberQueryService.getMemberBySocialId(socialId);

        if (subscribeQueryService.isAbleToSubscribe(member)) {
            Subscribe save = subscribeSaveService.save(subscribeMapper.mapToSubscribe(member, issueId));
            return subscribeMapper.mapToSubscribeRes(save).setSubscribe();

        }else{
            throw new NoAccessSubscribe();
        }

    }

    // 마이페이지에 보여줄 내 구독 목록 & contentService와 feign 통신 .
    public SubscribeMemberDto getAllSubscribeByMember(String socialId) {
        Member member = memberQueryService.getMemberBySocialId(socialId);
        return subscribeQueryService.subscribeListByMember(member);

    }



}
