package gwangjang.server.domain.auth.exception;

import gwangjang.server.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public class AppIdInvalidException extends AuthException{
    public AppIdInvalidException() {
        super(ErrorCode.APPID_INVALID_ERROR,
                HttpStatus.CONFLICT);
    }
}
