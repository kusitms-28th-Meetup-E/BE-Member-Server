package gwangjang.server.domain.subscribe.domain.repository;

import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.subscribe.adapter.producer.web.dto.MainBubbleRes;
import gwangjang.server.domain.subscribe.adapter.producer.web.dto.SubscribeData;
import gwangjang.server.domain.subscribe.adapter.producer.web.dto.SubscribersByIssueDto;
import gwangjang.server.domain.subscribe.application.dto.res.IssueBySubscribersRes;
import gwangjang.server.domain.subscribe.application.dto.res.SubscribeIssueFeignRes;
import gwangjang.server.domain.subscribe.application.dto.res.SubscribeMemberDto;
import gwangjang.server.domain.subscribe.domain.entity.Subscribe;

import java.util.List;

public interface SubscribeCustomRepository {

    SubscribeMemberDto findAllSubscribeByMember(Member member);
    Subscribe findSubscribeByMemberAndTopic(Member member, Long issueId);
    boolean findCountSubscribeByMember(Member member);
    List<IssueBySubscribersRes> findIssueTop5BySubscribers();
    Long findSubscribeCountsByIssue(Long issueId);
    List<SubscribeIssueFeignRes> findMySubscribeList(Member member);

    List<SubscribeData> getIssueBySubscribers();




    }
