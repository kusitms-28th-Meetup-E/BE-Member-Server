package gwangjang.server.domain.subscribe.domain.repository;

import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.subscribe.application.dto.res.SubscribeMemberDto;
import gwangjang.server.domain.subscribe.domain.entity.Subscribe;

public interface SubscribeCustomRepository {

    SubscribeMemberDto findAllSubscribeByMember(Member member);
    Subscribe findSubscribeByMemberAndTopic(Member member, String topic, String issue);
    boolean findCountSubscribeByMember(Member member);

}
