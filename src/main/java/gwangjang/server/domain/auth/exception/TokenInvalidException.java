package gwangjang.server.domain.auth.exception;

import gwangjang.server.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public class TokenInvalidException extends AuthException {
    public TokenInvalidException() {
        super(ErrorCode.TOKEN_INVALID_ERROR,
                HttpStatus.CONFLICT);
    }
}
