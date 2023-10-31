package gwangjang.server.domain.auth.application.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class KakaoAuthTokenResponse {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String id_token;
    private Integer expires_in;
    private String scope;
    private Integer refresh_token_expires_in;

    @Override
    public String toString() {
        return "KakaoAuthTokenResponse{" +
                "access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", id_token='" + id_token + '\'' +
                ", expires_in=" + expires_in +
                ", scope='" + scope + '\'' +
                ", refresh_token_expires_in=" + refresh_token_expires_in +
                '}';
    }
}

