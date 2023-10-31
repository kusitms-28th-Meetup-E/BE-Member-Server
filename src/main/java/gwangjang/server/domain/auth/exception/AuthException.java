package gwangjang.server.domain.auth.exception;

import gwangjang.server.global.exception.ApplicationException;
import gwangjang.server.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public abstract class AuthException extends ApplicationException {
    protected AuthException(ErrorCode errorCode, HttpStatus httpStatus) {
        super(errorCode, httpStatus);
    }
}
