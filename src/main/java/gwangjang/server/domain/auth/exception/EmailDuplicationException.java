package gwangjang.server.domain.auth.exception;

import gwangjang.server.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public class EmailDuplicationException extends AuthException {
    public EmailDuplicationException(){
        super(ErrorCode.EMAIL_DUPLICATION_ERROR,
                HttpStatus.BAD_REQUEST);
    }
}
