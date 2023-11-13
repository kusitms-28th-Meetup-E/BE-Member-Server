package gwangjang.server.domain.auth.presentation.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthResponseMessage {
    SIGN_IN_SUCCESS("로그인을 했습니다"),
    SIGN_UP_SUCCESS("회원 가입을 했습니다"),
    REISSUE_TOKEN_SUCCESS("토큰을 재발급했습니다"),
    CHECK_NICKNAME_SUCCESS("닉네임 중복검사를 했습니다"),
    CHECK_EMAIL_SUCCESS("이메일 인증을 요청 했습니다"),
    CHECK_LOGINID_SUCCESS("아이디 중복검사를 했습니다"),
    CHECK_EMAIL_AUTH_SUCCESS("이메일 인증을 성공 했습니다"),
    KAKAO_CALL_BACK_SUCCESS("카카오 리다이렉트를 완료 했습니다");
    private final String message;
}

