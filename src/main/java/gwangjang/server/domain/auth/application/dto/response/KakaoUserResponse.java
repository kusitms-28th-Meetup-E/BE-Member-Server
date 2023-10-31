package gwangjang.server.domain.auth.application.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class KakaoUserResponse {
    private Long id;
    private Properties properties;
    private KakaoAccount kakaoAccount;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Properties {
        private String nickname;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KakaoAccount {
        private String email;
    }

    public void adaptResponse() {
        if(kakaoAccount.email.length() > 50) kakaoAccount.email = kakaoAccount.email.substring(0, 50);
    }

    @Override
    public String toString() {
        return "KakaoUserResponse{" +
                "id=" + id +
                ", properties=" + properties +
                ", kakaoAccount=" + kakaoAccount +
                '}';
    }
}
