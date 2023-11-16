package gwangjang.server.domain.subscribe.domain.service;

import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.subscribe.application.dto.res.SubscribeMemberDto;
import gwangjang.server.domain.subscribe.domain.entity.Subscribe;
import gwangjang.server.domain.subscribe.domain.repository.SubscribeRepository;
import gwangjang.server.global.feign.client.FindIssueFeignClient;
import gwangjang.server.global.feign.dto.response.IssueDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//@DomainService
@Service
@RequiredArgsConstructor
public class SubscribeQueryService {

    private final SubscribeRepository subscribeRepository;
//    private final FindIssueFeignClient findIssueFeignClient;

    public SubscribeMemberDto subscribeListByMember(Member member) {
        SubscribeMemberDto subscribeMemberDto = subscribeRepository.findAllSubscribeByMember(member);
        subscribeMemberDto.getSubscribeResList().stream().forEach(
                subscribeMyPageRes ->
                {
                    Long issueId = subscribeMyPageRes.getIssueId();
//                    IssueDto issueDto = findIssueFeignClient.getIssueByIssueId(issueId);
//                    subscribeMyPageRes.updateNames(issueDto);

                }
        );

        return subscribeMemberDto;
    }

    public Subscribe findSubscribeByMemberAndTopic(Member member, Long topicId, Long issueId) {
        return subscribeRepository.findSubscribeByMemberAndTopic(member,issueId);
    }

    public boolean isAbleToSubscribe(Member member) {
        return subscribeRepository.findCountSubscribeByMember(member);
    }
}
