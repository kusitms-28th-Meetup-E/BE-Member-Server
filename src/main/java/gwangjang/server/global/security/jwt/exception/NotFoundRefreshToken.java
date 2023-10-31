package gwangjang.server.global.security.jwt.exception;

import gwangjang.server.global.exception.ApplicationException;
import gwangjang.server.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundRefreshToken extends ApplicationException {
    public NotFoundRefreshToken() {
        super(ErrorCode.NOT_FOUND_REFRESH_TOKEN_ERROR,
                HttpStatus.NOT_FOUND);
    }

}
