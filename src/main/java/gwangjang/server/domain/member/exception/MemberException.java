package gwangjang.server.domain.member.exception;

import gwangjang.server.global.exception.ApplicationException;
import gwangjang.server.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public abstract class MemberException  extends ApplicationException {

    protected MemberException(ErrorCode errorCode, HttpStatus httpStatus) {
        super(errorCode, httpStatus);
    }
}
