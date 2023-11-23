package gwangjang.server.global.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class TokenInfoResponse {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long refreshTokenExpirationTime;
    private String memberId;

    public static TokenInfoResponse from(String grantType, String accessToken, String refreshToken, Long refreshTokenExpirationTime,String socialId) {
        return TokenInfoResponse.builder()
                .grantType(grantType)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .refreshTokenExpirationTime(refreshTokenExpirationTime)
                .memberId(socialId)
                .build();
    }

    public void updateRefreshToken(String refreshToken){
        this.refreshToken=refreshToken;
    }
}

