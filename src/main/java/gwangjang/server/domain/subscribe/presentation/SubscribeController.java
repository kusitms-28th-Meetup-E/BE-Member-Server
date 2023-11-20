package gwangjang.server.domain.subscribe.presentation;


import gwangjang.server.domain.subscribe.application.dto.res.*;
import gwangjang.server.domain.subscribe.application.service.SubscribeContentsUseCase;
import gwangjang.server.domain.subscribe.application.service.SubscribeReadUseCase;
import gwangjang.server.domain.subscribe.application.service.SubscribeUseCase;
import gwangjang.server.domain.subscribe.application.service.UnSubscribeUseCase;
import gwangjang.server.global.response.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static gwangjang.server.domain.subscribe.presentation.constant.SubscribeResponse.*;

@RestController
@AllArgsConstructor
@RequestMapping()
public class SubscribeController {


    private final SubscribeUseCase subscribeUseCase;
    private final UnSubscribeUseCase unSubscribeUseCase;

    private final SubscribeReadUseCase subscribeReadUseCase;
    private final SubscribeContentsUseCase subscribeContentsUseCase;

    @PostMapping("/topic/{topicId}/issue/{issueId}/subscribe")
    public ResponseEntity<SuccessResponse<SubscribeRes>> subscribe(@RequestHeader(value = "user-id") String socialId,
                                                                   @PathVariable("topicId") Long topicId, @PathVariable("issueId") Long issueId) {
        return ResponseEntity.ok(SuccessResponse.create(SUBSCRIBE_SUCCESS.getMessage(), this.subscribeUseCase.subscribe(socialId, topicId, issueId)));
    }

    @DeleteMapping("/topic/{topicId}/issue/{issueId}/subscribe")
    public ResponseEntity<SuccessResponse<SubscribeRes>> unSubscribe(@RequestHeader(value = "user-id") String socialId,
                                                                     @PathVariable("topicId") Long topicId, @PathVariable("issueId") Long issueId) {
        return ResponseEntity.ok(SuccessResponse.create(UNSUBSCRIBE_SUCCESS.getMessage(), this.unSubscribeUseCase.unSubscribe(socialId,topicId, issueId)));
    }


    /**
     * 마이페이지 나의 구독 목록 & contentService와 feign 통신 .
     * @return
     */
    @GetMapping("/subscribe")
    public ResponseEntity<SuccessResponse<SubscribeMemberDto>> getMySubscribe(@RequestHeader(value = "user-id") String socialId) {
        return ResponseEntity.ok(SuccessResponse.create(GET_MY_SUBSCRIBES_SUCCESS.getMessage(), this.subscribeUseCase.getAllSubscribeByMember(socialId)));
    }

    /**
     * 구독 순 주제 top 5
     * @param socialId
     * @return
     */
    @GetMapping("/subscribe/issue")
    public ResponseEntity<SuccessResponse<List<IssueBySubscribersRes>>> getIssuesBySubscribers(@RequestHeader(value = "user-id") String socialId) {
        return ResponseEntity.ok(SuccessResponse.create(GET_MY_SUBSCRIBES_SUCCESS.getMessage(), this.subscribeReadUseCase.getIssueBySubscribers()));
    }

     /**
     * 주제 상세페이지 구독자 수
     * @param issueId
     * @return
     */
    @GetMapping("/subscribe/issue/{issueId}")
    public ResponseEntity<SuccessResponse<IssueSubscribeInfoRes>> getIssueSubscribers(@PathVariable("issueId") Long issueId) {
        return ResponseEntity.ok(SuccessResponse.create(GET_SUBSCRIBERS_BY_ISSUE.getMessage(), this.subscribeReadUseCase.getSubscribeIssueInfo(issueId)));
    }
     /**
     * 로그인 한 사용자 구독 정보 (List<Long>) -> contents
     * @param
     * @return
     */
    @GetMapping("/subscribe/contents")
    public ResponseEntity<SuccessResponse<List<Long>>> getMySubscribeIssueList(@RequestHeader(value = "user-id") String socialId) {
        return ResponseEntity.ok(SuccessResponse.create(GET_MY_SUBSCRIBES_SUCCESS.getMessage(), this.subscribeContentsUseCase.getMySubscribeIssueList(socialId)));
    }






}