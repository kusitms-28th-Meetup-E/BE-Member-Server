package gwangjang.server.domain.subscribe.domain.repository;

import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.subscribe.application.dto.res.IssueBySubscribersRes;
import gwangjang.server.domain.subscribe.application.dto.res.SubscribeMemberDto;
import gwangjang.server.domain.subscribe.domain.entity.Subscribe;

import java.util.List;

public interface SubscribeCustomRepository {

    SubscribeMemberDto findAllSubscribeByMember(Member member);
    Subscribe findSubscribeByMemberAndTopic(Member member, Long issueId);
    boolean findCountSubscribeByMember(Member member);
    List<IssueBySubscribersRes> findIssueTop5BySubscribers();
    Long findSubscribeCountsByIssue(Long issueId);
    List<Long> findMySubscribeList(Member member);



    }
