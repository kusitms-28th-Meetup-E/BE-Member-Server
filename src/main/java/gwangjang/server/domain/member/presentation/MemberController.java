package gwangjang.server.domain.member.presentation;

import gwangjang.server.domain.auth.application.dto.request.*;
import gwangjang.server.domain.auth.application.dto.response.CheckEmailResponse;
import gwangjang.server.domain.auth.application.dto.response.CheckNicknameResponse;
import gwangjang.server.domain.auth.application.dto.response.ReissueTokenResponse;
import gwangjang.server.domain.auth.application.dto.response.SignInResponse;
import gwangjang.server.domain.auth.application.service.*;
import gwangjang.server.domain.auth.application.service.kakao.KakaoTokenUserCase;
import gwangjang.server.domain.member.application.service.MessageWithCommunityUseCase;
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
//@CrossOrigin(origins = "*")
//@RequestMapping(value = "/auth",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
@RequestMapping(value = "/auth")
@Slf4j
public class MemberController {


    private final MessageWithCommunityUseCase messageWithCommunityUseCase;

    @PostMapping("/nickname/kafka")
    public ResponseEntity<SuccessResponse<String>> checkEmailAuth(){
        messageWithCommunityUseCase.sendToCommunity("LOCAL@328a72a5-2a3b-450a-8812-4a1ed212eb55");
        return ResponseEntity.ok(SuccessResponse.create(CHECK_EMAIL_AUTH_SUCCESS.getMessage(),"done"));
    }


}