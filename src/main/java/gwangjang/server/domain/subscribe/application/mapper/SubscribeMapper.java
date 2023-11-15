package gwangjang.server.domain.subscribe.application.mapper;

import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.subscribe.application.dto.res.SubscribeRes;
import gwangjang.server.domain.subscribe.domain.entity.Subscribe;
import gwangjang.server.global.annotation.Mapper;

@Mapper
public class SubscribeMapper {

    public Subscribe mapToSubscribe(Member member, String topic,String issue) {
        return Subscribe.builder()
                .member(member)
                .issue(issue)
                .topic(topic)
                .build();
    }

    public SubscribeRes mapToSubscribeRes(Subscribe subscribe) {
        return SubscribeRes.builder()
                .issue(subscribe.getIssue())
                .topic(subscribe.getTopic())
                .status(Boolean.TRUE)
                .build();
    }



}
