package gwangjang.server.domain.auth.exception;

import gwangjang.server.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public class EmailAuthException extends AuthException {
    public EmailAuthException() {
        super(ErrorCode.EMAIL_AUTH_ERROR,
                HttpStatus.UNAUTHORIZED);
    }
}

