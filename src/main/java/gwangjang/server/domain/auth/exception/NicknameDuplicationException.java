package gwangjang.server.domain.auth.exception;

import gwangjang.server.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public class NicknameDuplicationException extends AuthException {
    public NicknameDuplicationException(){
        super(ErrorCode.NICKNAME_DUPLICATION_ERROR,
                HttpStatus.BAD_REQUEST);
    }
}
