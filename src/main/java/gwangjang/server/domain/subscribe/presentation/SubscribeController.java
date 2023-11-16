package gwangjang.server.domain.subscribe.presentation;


import gwangjang.server.domain.subscribe.application.dto.res.SubscribeMemberDto;
import gwangjang.server.domain.subscribe.application.dto.res.SubscribeRes;
import gwangjang.server.domain.subscribe.application.service.SubscribeUseCase;
import gwangjang.server.domain.subscribe.application.service.UnSubscribeUseCase;
import gwangjang.server.global.response.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static gwangjang.server.domain.subscribe.presentation.constant.SubscribeResponse.*;

@RestController
@AllArgsConstructor
@RequestMapping()
public class SubscribeController {


    private final SubscribeUseCase subscribeUseCase;
    private final UnSubscribeUseCase unSubscribeUseCase;


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
}