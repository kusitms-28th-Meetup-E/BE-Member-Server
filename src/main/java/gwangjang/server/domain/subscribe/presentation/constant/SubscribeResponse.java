package gwangjang.server.domain.subscribe.presentation.constant;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubscribeResponse {


    SUBSCRIBE_SUCCESS("주제 구독을 완료 했습니다."),
    UNSUBSCRIBE_SUCCESS("주제 구독 취소를 완료 했습니다."),
    GET_MY_SUBSCRIBES_SUCCESS("내가 구독한 주제 조회를 완료했습니다");

    private final String message;

}