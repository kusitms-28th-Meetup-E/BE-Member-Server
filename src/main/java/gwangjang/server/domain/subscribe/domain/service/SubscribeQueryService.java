package gwangjang.server.domain.subscribe.domain.service;

import gwangjang.server.domain.FindKeywordFeignClient;
import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.subscribe.adapter.producer.web.dto.MainBubbleRes;
import gwangjang.server.domain.subscribe.adapter.producer.web.dto.SubscribeData;
import gwangjang.server.domain.subscribe.adapter.producer.web.dto.SubscribersByIssueDto;
import gwangjang.server.domain.subscribe.application.dto.res.*;
import gwangjang.server.domain.subscribe.domain.entity.Subscribe;
import gwangjang.server.domain.subscribe.domain.repository.SubscribeRepository;
import gwangjang.server.global.annotation.DomainService;
import gwangjang.server.global.feign.dto.response.IssueDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@DomainService
@RequiredArgsConstructor
public class SubscribeQueryService {

    private final SubscribeRepository subscribeRepository;
    private final FindKeywordFeignClient findKeywordFeignClient;

    public SubscribeMemberDto subscribeListByMember(Member member) {
        SubscribeMemberDto subscribeMemberDto = subscribeRepository.findAllSubscribeByMember(member);
        subscribeMemberDto.getSubscribeResList().stream().forEach(
                subscribeMyPageRes ->
                {
                    Long issueId = subscribeMyPageRes.getIssueId();
                    IssueDto issueDto = findKeywordFeignClient.
                            getIssueByIssueId(issueId).getBody().getData();
                    log.info("!!!"+issueDto.getIssueTitle());
                    subscribeMyPageRes.updateNames(issueDto);
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

    public List<IssueBySubscribersRes> getCountSubscribers() {
        List<IssueBySubscribersRes> issueTop5BySubscribers = subscribeRepository.findIssueTop5BySubscribers();


        issueTop5BySubscribers.stream().forEach(
                issueBySubscribeDto -> {
                    Long issueId = issueBySubscribeDto.getTitleId();
                    IssueDto issueDto = findKeywordFeignClient.
                            getIssueByIssueId(issueId).getBody().getData();
                    issueBySubscribeDto.updateByIssueDto(issueDto);

                }
        );

        return issueTop5BySubscribers;

    }


    public IssueSubscribeInfoRes getIssueInfo(Long issueId) {
        IssueSubscribeInfoRes issueSubscribeInfoRes = new IssueSubscribeInfoRes();

        Long subscribeCountsByIssue = subscribeRepository.findSubscribeCountsByIssue(issueId);
        issueSubscribeInfoRes.updateSubscribers(subscribeCountsByIssue);

        return issueSubscribeInfoRes;

    }

    public List<IssueBySubscribeRecommendRes> getIssueInfo(List<Long> issueIds) {
        List<IssueBySubscribeRecommendRes> issueBySubscribeRecommendRes = new ArrayList<>();

        issueIds.stream().forEach(
                issueId -> {

                    IssueBySubscribeRecommendRes issueInfoRes = new IssueBySubscribeRecommendRes();

                    Long subscribeCountsByIssue = subscribeRepository.findSubscribeCountsByIssue(issueId);
                    issueInfoRes.updateSubscribers(subscribeCountsByIssue);
                    IssueDto issueDto = findKeywordFeignClient.
                            getIssueByIssueId(issueId).getBody().getData();
                    issueInfoRes.updateIssueInfo(issueDto);
                }
        );

        return issueBySubscribeRecommendRes;

    }

    public List<SubscribeIssueFeignRes> getMySubscribeList(Member member) {
        List<SubscribeIssueFeignRes> mySubscribeList = subscribeRepository.findMySubscribeList(member);
        mySubscribeList.stream().forEach(
                mySubscribe->{
                    Long issueId = mySubscribe.getIssueId();

                    IssueDto issueDto = findKeywordFeignClient.
                            getIssueByIssueId(issueId).getBody().getData();
                    mySubscribe.updateIssue(issueDto.getIssueTitle());
                }
        );
        return mySubscribeList;
    }

    public Long getSubscribers(Long issueId) {
        return subscribeRepository.findSubscribeCountsByIssue(issueId);
    }


    public List<SubscribeData> getIssueBySubscribers() {
        return subscribeRepository.getIssueBySubscribers();
    }





}
