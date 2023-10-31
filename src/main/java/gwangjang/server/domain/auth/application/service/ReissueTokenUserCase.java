package gwangjang.server.domain.auth.application.service;

import gwangjang.server.domain.auth.application.dto.response.ReissueTokenResponse;
import gwangjang.server.global.security.jwt.exception.NotFoundRefreshToken;
import gwangjang.server.global.security.jwt.service.TokenUtil;
import gwangjang.server.global.response.TokenInfoResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class ReissueTokenUserCase {

    private final TokenUtil tokenUtil;

    public ReissueTokenResponse reissueToken(String token) {
        // refresh 토큰이 유효한지 확인
        if (token != null && tokenUtil.verifyToken(token)) {

            // 토큰 새로 받아오기
            TokenInfoResponse newToken = tokenUtil.tokenReissue(token);

            return ReissueTokenResponse.from(newToken);
        }
        throw new NotFoundRefreshToken();
    }
}
