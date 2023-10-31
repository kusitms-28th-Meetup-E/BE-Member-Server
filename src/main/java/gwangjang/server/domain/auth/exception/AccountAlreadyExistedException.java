package gwangjang.server.domain.auth.exception;

import gwangjang.server.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public class AccountAlreadyExistedException extends AuthException {
    public AccountAlreadyExistedException() {
        super(ErrorCode.ACCOUNT_ALREADY_EXIST,
                HttpStatus.UNAUTHORIZED);
    }
}

