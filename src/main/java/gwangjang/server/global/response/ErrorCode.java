package gwangjang.server.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    BAD_REQUEST("400", "입력값이 유효하지 않습니다."),
    METHOD_NOT_ALLOWED("405", "클라이언트가 사용한 HTTP 메서드가 리소스에서 허용되지 않습니다."),
    INTERNAL_SERVER_ERROR("500", "서버에서 요청을 처리하는 동안 오류가 발생했습니다."),
    NOT_FOUND_REFRESH_TOKEN_ERROR( "J0008",  "유효하지 않는 RefreshToken 입니다."),

    //유저 관련 에러 코드
    NOT_FOUND_BY_SOCIAL_ID_ERROR( "U0001",  "해당 socialId인 유저가 존재하지 않습니다."),
    ACCOUNT_ALREADY_EXIST("AU0001", "해당 email로 다른 소셜 플랫폼으로 가입하였습니다."),
    TOKEN_INVALID_ERROR("AU0002", "입력 토큰이 유효하지 않습니다."),
    APPID_INVALID_ERROR("AU0003", "appId가 유효하지 않습니다"),
    NICKNAME_DUPLICATION_ERROR("AU0004", "닉네임이 중복됩니다."),
    EMAIL_DUPLICATION_ERROR("AU0005", "이메일이 중복됩니다."),
    EMAIL_AUTH_ERROR("AU0005", "이메일이 중복됩니다."),


    NO_MORE_SUBSCRIBE("S001","3개 이상 구독할 수 없습니다"),
    NOT_FOUND_BY_LOGIN_ID_ERROR("L001","해당 id인 유저가 존재하지 않습니다"),
    INCORRECT_PASSWORD("L002","비밀번호가 틀렸습니다");


    private String errorCode;
    private String message;

}