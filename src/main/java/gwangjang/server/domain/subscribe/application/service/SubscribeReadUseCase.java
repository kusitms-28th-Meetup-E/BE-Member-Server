package gwangjang.server.domain.subscribe.application.service;

import gwangjang.server.domain.subscribe.adapter.producer.web.dto.SubscribeData;
import gwangjang.server.domain.subscribe.adapter.producer.web.dto.SubscribersByIssueDto;
import gwangjang.server.domain.subscribe.application.dto.res.IssueBySubscribersRes;
import gwangjang.server.domain.subscribe.application.dto.res.IssueBySubscribeRecommendRes;
import gwangjang.server.domain.subscribe.application.dto.res.IssueSubscribeInfoRes;
import gwangjang.server.domain.subscribe.application.mapper.SubscribeMapper;
import gwangjang.server.domain.subscribe.domain.service.SubscribeQueryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscribeReadUseCase {

    private final SubscribeQueryService subscribeQueryService;
    private final SubscribeMapper subscribeMapper = new SubscribeMapper();


    public List<IssueBySubscribersRes> getIssueBySubscribers() {
        return subscribeQueryService.getCountSubscribers();
    }

    public IssueSubscribeInfoRes getSubscribeIssueInfo(Long issueId) {
        return subscribeQueryService.getIssueInfo(issueId);
    }

    public List<SubscribeData> getAllIssueBySubscribers() {
        return subscribeQueryService.getIssueBySubscribers();
    }



}
