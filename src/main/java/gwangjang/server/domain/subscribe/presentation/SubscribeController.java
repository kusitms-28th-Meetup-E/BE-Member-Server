package gwangjang.server.domain.subscribe.presentation;


import gwangjang.server.domain.subscribe.application.dto.res.SubscribeRes;
import gwangjang.server.domain.subscribe.application.service.SubscribeUseCase;
import gwangjang.server.domain.subscribe.application.service.UnSubscribeUseCase;
import gwangjang.server.global.response.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static gwangjang.server.domain.subscribe.presentation.constant.SubscribeResponse.SUBSCRIBE_SUCCESS;

@RestController
@AllArgsConstructor
@RequestMapping()
public class SubscribeController {


    private final SubscribeUseCase subscribeUseCase;
    private final UnSubscribeUseCase unSubscribeUseCase;


    @PostMapping("/topic/{topic}/issue/{issue}/subscribe")
    public ResponseEntity<SuccessResponse<SubscribeRes>> subscribe(@RequestHeader(value = "user-id") String socialId,
                                                                   @PathVariable("topic") String topic, @PathVariable("issue") String issue) {
        return ResponseEntity.ok(SuccessResponse.create(SUBSCRIBE_SUCCESS.getMessage(), this.subscribeUseCase.subscribe(socialId, topic,issue)));
    }

    @DeleteMapping("/topic/{topic}/issue/{issue}/subscribe")
    public ResponseEntity<SuccessResponse<SubscribeRes>> unSubscribe(@RequestHeader(value = "user-id") String socialId,
                                                                   @PathVariable("topic") String topic, @PathVariable("issue") String issue) {
        return ResponseEntity.ok(SuccessResponse.create(SUBSCRIBE_SUCCESS.getMessage(), this.unSubscribeUseCase.unSubscribe(socialId, topic,issue)));
    }
}
