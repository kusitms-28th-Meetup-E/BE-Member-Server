package gwangjang.server.domain.auth.presentation;

import gwangjang.server.domain.auth.application.dto.request.LocalSignInRequest;
import gwangjang.server.domain.auth.application.dto.request.SignInRequest;
import gwangjang.server.domain.auth.application.dto.request.SignUpRequest;
import gwangjang.server.domain.auth.application.dto.request.TestRequest;
import gwangjang.server.domain.auth.application.dto.response.CheckNicknameResponse;
import gwangjang.server.domain.auth.application.dto.response.ReissueTokenResponse;
import gwangjang.server.domain.auth.application.dto.response.SignInResponse;
import gwangjang.server.domain.auth.application.service.CheckNicknameUserCase;
import gwangjang.server.domain.auth.application.service.ReissueTokenUserCase;
import gwangjang.server.domain.auth.application.service.SignInUserCase;
import gwangjang.server.domain.auth.application.service.SignUpUserCase;
import gwangjang.server.domain.auth.application.service.kakao.KakaoTokenUserCase;
import gwangjang.server.global.response.SuccessResponse;
import gwangjang.server.global.security.dto.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import static gwangjang.server.domain.auth.presentation.constant.AuthResponseMessage.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final SignInUserCase authService;

    private final SignUpUserCase signUpService;

    private final ReissueTokenUserCase reissueTokenService;
    private final CheckNicknameUserCase checkNicknameService;
    private final KakaoTokenUserCase kakaoTokenUserCase;



    @PostMapping("/signIn")
    public ResponseEntity<SuccessResponse<SignInResponse>> signIn(
                                                                  @Valid @RequestBody LocalSignInRequest localSignInRequest) {
        return ResponseEntity.ok(SuccessResponse.create(SIGN_IN_SUCCESS.getMessage(), this.authService.localSignIn(localSignInRequest)));
    }

    @PostMapping("/signIn/{provider}")
    public ResponseEntity<SuccessResponse<SignInResponse>> socialSignIn(@PathVariable String provider,
                                                                  @Valid @RequestBody SignInRequest signInRequest) {
        log.info("kakao login Hi");
        return ResponseEntity.ok(SuccessResponse.create(SIGN_IN_SUCCESS.getMessage(), this.authService.signIn(signInRequest.getToken(), provider)));
    }


    @PutMapping("/signUp")
    public ResponseEntity<SuccessResponse<SignInResponse>> socialSignUp(@RequestHeader(value = "Authorization") String token,
                                                                  @Valid @RequestBody SignUpRequest signUpRequest) {
        token = (token != null && token.startsWith("Bearer ")) ? token.substring(7) : token;
        return ResponseEntity.ok(SuccessResponse.create(SIGN_UP_SUCCESS.getMessage(), this.signUpService.signUp(token, signUpRequest)));
    }

    @GetMapping("/reissue")
    public ResponseEntity<SuccessResponse<ReissueTokenResponse>> reissue(@RequestHeader(value = "RefreshToken") String token) {
        ReissueTokenResponse reissueToken = reissueTokenService.reissueToken(token);
        return ResponseEntity.ok(SuccessResponse.create(REISSUE_TOKEN_SUCCESS.getMessage(), reissueToken));
    }


    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<SuccessResponse<CheckNicknameResponse>> checkNickname(@PathVariable String nickname){
        return ResponseEntity.ok(SuccessResponse.create(CHECK_NICKNAME_SUCCESS.getMessage(), checkNicknameService.checkNickname(nickname)));
    }


    @PostMapping("/test/{provider}")
    public ResponseEntity<SuccessResponse<SignInResponse>> testLogin(@PathVariable String provider,
                                                                     @RequestBody TestRequest testRequest){
        return ResponseEntity.ok(SuccessResponse.create(SIGN_IN_SUCCESS.getMessage(), this.authService.testSignIn(testRequest.getSocialId(),provider)));
    }

    @PostMapping("/test/hi")
    public ResponseEntity<SuccessResponse<String>> test(@AuthenticationPrincipal User user
                                                                  ){

        log.info("/test/hi -> start ");
        log.info(user.getEmail());
        return ResponseEntity.ok(SuccessResponse.create(SIGN_IN_SUCCESS.getMessage(),user.getEmail()));
    }

    @GetMapping("/oauth/kakao")
    public ResponseEntity<SuccessResponse<String>> kakaoCallBack(@RequestParam String code){
        log.info("/oauth/kakao redirect success");
        return ResponseEntity.ok(SuccessResponse.create(KAKAO_CALL_BACK_SUCCESS.getMessage(),kakaoTokenUserCase.getAccessToken(code).getAccess_token()));
    }


}