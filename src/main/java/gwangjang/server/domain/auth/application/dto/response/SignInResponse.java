package gwangjang.server.domain.auth.application.dto.response;

import gwangjang.server.domain.member.domain.entity.constant.RegistrationStatus;
import gwangjang.server.global.response.TokenInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {
    private String accessToken;
    private String refreshToken;
    private Boolean registrationStatus;

    public static SignInResponse from(TokenInfoResponse tokenInfoResponse, RegistrationStatus registrationStatus) {
        return SignInResponse.builder()
                .accessToken(tokenInfoResponse.getAccessToken())
                .refreshToken(tokenInfoResponse.getRefreshToken())
                .registrationStatus(registrationStatus.equals(RegistrationStatus.COMPLETED))
                .build();
    }
}
