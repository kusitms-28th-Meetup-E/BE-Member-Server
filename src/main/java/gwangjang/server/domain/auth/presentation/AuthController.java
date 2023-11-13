package gwangjang.server.domain.auth.presentation;

import gwangjang.server.domain.auth.application.dto.request.*;
import gwangjang.server.domain.auth.application.dto.response.*;
import gwangjang.server.domain.auth.application.service.*;
import gwangjang.server.domain.auth.application.service.kakao.KakaoTokenUserCase;
import gwangjang.server.global.response.SuccessResponse;
import gwangjang.server.global.security.dto.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static gwangjang.server.domain.auth.presentation.constant.AuthResponseMessage.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/auth")
@Slf4j
public class AuthController {

    private final SignInUserCase authService;

    private final SignUpUserCase signUpService;

    private final ReissueTokenUserCase reissueTokenService;
    private final CheckNicknameUserCase checkNicknameService;
    private final KakaoTokenUserCase kakaoTokenUserCase;
    private final CheckEmailUserCase checkEmailUserCase;
    private final CheckLoginIdUseCase checkLoginIdUseCase;


    @PostMapping("/signIn")
    public ResponseEntity<SuccessResponse<SignInResponse>> signIn(@RequestBody LocalSignInRequest localSignInRequest) {
        return ResponseEntity.ok(SuccessResponse.create(SIGN_IN_SUCCESS.getMessage(), this.authService.localSignIn(localSignInRequest)));
    }

    @PostMapping("/signIn/{provider}")
    public ResponseEntity<SuccessResponse<SignInResponse>> socialSignIn(@PathVariable String provider,
                                                                        @RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(SuccessResponse.create(SIGN_IN_SUCCESS.getMessage(), this.authService.signIn(signInRequest.getToken(), provider)));
    }


    @PutMapping("/signUp/{provider}")
    public ResponseEntity<SuccessResponse<SignInResponse>> socialSignUp(@PathVariable String provider,
                                                                        @RequestHeader(value = "Authorization") String token,
                                                                        @RequestBody SignUpRequest signUpRequest) {
        token = (token != null && token.startsWith("Bearer ")) ? token.substring(7) : token;
        return ResponseEntity.ok(SuccessResponse.create(SIGN_UP_SUCCESS.getMessage(), this.signUpService.signUp(token, signUpRequest)));
    }

    @PostMapping("/signUp")
    public ResponseEntity<SuccessResponse<SignInResponse>> signUp(@RequestBody LocalSignUpRequest signUpRequest) {
        return ResponseEntity.ok(SuccessResponse.create(SIGN_UP_SUCCESS.getMessage(), this.signUpService.localSignUp(signUpRequest)));
    }

    @GetMapping("/reissue")
    public ResponseEntity<SuccessResponse<ReissueTokenResponse>> reissue(@RequestHeader(value = "RefreshToken") String token) {
        ReissueTokenResponse reissueToken = reissueTokenService.reissueToken(token);
        return ResponseEntity.ok(SuccessResponse.create(REISSUE_TOKEN_SUCCESS.getMessage(), reissueToken));
    }


    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<SuccessResponse<CheckNicknameResponse>> checkNickname(@PathVariable String nickname) {
        return ResponseEntity.ok(SuccessResponse.create(CHECK_NICKNAME_SUCCESS.getMessage(), checkNicknameService.checkNickname(nickname)));
    }


    @PostMapping("/test/{provider}")
    public ResponseEntity<SuccessResponse<SignInResponse>> testLogin(@PathVariable String provider,
                                                                     @RequestBody TestRequest testRequest) {
        return ResponseEntity.ok(SuccessResponse.create(SIGN_IN_SUCCESS.getMessage(), this.authService.testSignIn(testRequest.getSocialId(), provider)));
    }

    @GetMapping("/oauth/kakao")
    public ResponseEntity<SuccessResponse<String>> kakaoCallBack(@RequestParam String code) {
        log.info("/oauth/kakao redirect success");
        return ResponseEntity.ok(SuccessResponse.create(KAKAO_CALL_BACK_SUCCESS.getMessage(), kakaoTokenUserCase.getAccessToken(code).getAccess_token()));
    }

    @PostMapping("/email/{email}")
    public ResponseEntity<SuccessResponse<CheckEmailResponse>> sendEmailAuth(@PathVariable String email) {
        return ResponseEntity.ok(SuccessResponse.create(CHECK_EMAIL_SUCCESS.getMessage(), checkEmailUserCase.requestEmail(email)));
    }

    @PostMapping("/email")
    public ResponseEntity<SuccessResponse<CheckEmailResponse>> checkEmailAuth(@RequestBody CheckEmailRequest checkEmailRequest) {
        return ResponseEntity.ok(SuccessResponse.create(CHECK_EMAIL_AUTH_SUCCESS.getMessage(), checkEmailUserCase.checkEmailAuth(checkEmailRequest)));
    }

    @GetMapping("/loginId/{loginId}")
    public ResponseEntity<SuccessResponse<CheckLoginIdResponse>> sendIdDuplicate(@PathVariable String loginId) {
        return ResponseEntity.ok(SuccessResponse.create(CHECK_LOGINID_SUCCESS.getMessage(), checkLoginIdUseCase.checkLoginId(loginId)));
    }
}