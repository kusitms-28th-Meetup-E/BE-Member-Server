package gwangjang.server.domain.auth.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignInRequest {
    @NotBlank(message="token 을 입력해주세요.")
    private String token;
}

