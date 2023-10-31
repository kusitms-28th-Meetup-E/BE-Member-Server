package gwangjang.server.domain.auth.exception;

import gwangjang.server.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

import static gwangjang.server.global.response.ErrorCode.INCORRECT_PASSWORD;

public class InCorrectPasswordException extends AuthException {

    public InCorrectPasswordException() {
        super(INCORRECT_PASSWORD, HttpStatus.NOT_FOUND);
    }
}
