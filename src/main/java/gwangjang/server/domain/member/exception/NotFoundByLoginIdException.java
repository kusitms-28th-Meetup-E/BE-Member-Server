package gwangjang.server.domain.member.exception;

import gwangjang.server.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundByLoginIdException extends MemberException {

    public NotFoundByLoginIdException() {
        super(ErrorCode.NOT_FOUND_BY_LOGIN_ID_ERROR, HttpStatus.NOT_FOUND);
    }
}
