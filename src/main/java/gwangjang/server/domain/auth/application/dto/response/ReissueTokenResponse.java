package gwangjang.server.domain.auth.application.dto.response;

import gwangjang.server.global.response.TokenInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReissueTokenResponse {

    private String accessToken;
    private String refreshToken;

    public static ReissueTokenResponse from(TokenInfoResponse tokenInfoResponse) {
        return ReissueTokenResponse.builder()
                .accessToken(tokenInfoResponse.getAccessToken())
                .refreshToken(tokenInfoResponse.getRefreshToken())
                .build();
    }

}
