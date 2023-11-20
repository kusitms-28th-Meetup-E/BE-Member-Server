package gwangjang.server.domain.subscribe.application.mapper;

import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.subscribe.application.dto.res.SubscribeMyPageRes;
import gwangjang.server.domain.subscribe.application.dto.res.SubscribeRes;
import gwangjang.server.domain.subscribe.domain.entity.Subscribe;
import gwangjang.server.global.annotation.Mapper;
import gwangjang.server.global.feign.dto.response.IssueDto;

@Mapper
public class SubscribeMapper {

    public Subscribe mapToSubscribe(Member member,Long issueId) {
        return Subscribe.builder()
                .member(member)
                .issueId(issueId)
                .build();
    }

    public SubscribeRes mapToSubscribeRes(Subscribe subscribe) {
        return SubscribeRes.builder()
                .issueId(subscribe.getIssueId())
                .status(Boolean.TRUE)
                .build();
    }

    public SubscribeMyPageRes mapToSubscribeMyPageRes(IssueDto issueDto) {
        return SubscribeMyPageRes.builder()
                .topic(issueDto.getTopicTitle())
                .issue(issueDto.getIssueTitle())
                .imgUrl(issueDto.getImgUrl())
                .build();
    }



}
