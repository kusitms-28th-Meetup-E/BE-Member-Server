package gwangjang.server.domain.auth.application.service;

import gwangjang.server.domain.auth.application.dto.request.CheckEmailRequest;
import gwangjang.server.domain.auth.application.dto.response.CheckEmailResponse;
import gwangjang.server.domain.auth.application.dto.response.CheckNicknameResponse;
import gwangjang.server.domain.auth.exception.EmailAuthException;
import gwangjang.server.domain.member.domain.service.MemberCheckService;
import gwangjang.server.global.security.jwt.redis.RedisUtil;
import gwangjang.server.global.utils.EmailUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CheckEmailUserCase {

    private final MemberCheckService memberCheckService;

    private final EmailUtil emailUtil;
    private final RedisUtil redisUtil;

    private static final String AUTH_CODE_PREFIX = "AuthCode ";


    public CheckEmailResponse requestEmail(String email){
        boolean isDuplicated=memberCheckService.checkEmail(email);

        if (!isDuplicated) {
            log.info("createCode Before");
            String code = createCode();
            log.info("sendEmail Before");
            emailUtil.sendEmail(email,"광장 이메일 인증","인증번호 : "+code);
            redisUtil.save(code,email);
            log.info("code"+code);
        }
        return new CheckEmailResponse(isDuplicated);
    }


    public CheckEmailResponse checkEmailAuth(CheckEmailRequest checkEmailRequest) {

        return verifiedCode(checkEmailRequest.getEmail(), checkEmailRequest.getCode());
    }

    private String createCode() {
        int lenth = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < lenth; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            log.debug("MemberService.createCode() exception occur");
            throw new EmailAuthException();
        }
    }

    public CheckEmailResponse verifiedCode(String email, String authCode) {
        String redisAuthCode = redisUtil.getEmailValues (email).get().toString();
        log.info(String.valueOf(redisUtil.checkExistsValue(redisAuthCode))+"checkExistsValue");
        log.info(String.valueOf(redisAuthCode.equals(authCode))+"redisauthCodeEquals");
        boolean authResult = redisUtil.checkExistsValue(redisAuthCode) && redisAuthCode.equals(authCode);
        return new CheckEmailResponse(authResult);
    }
}
