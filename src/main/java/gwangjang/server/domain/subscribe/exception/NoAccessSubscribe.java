package gwangjang.server.domain.subscribe.exception;

import gwangjang.server.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public class NoAccessSubscribe extends SubscribeException {

    public NoAccessSubscribe() {
        super(ErrorCode.NO_MORE_SUBSCRIBE, HttpStatus.NOT_FOUND);
    }
}
