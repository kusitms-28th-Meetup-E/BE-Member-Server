package gwangjang.server.domain.auth.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignInRequest {
    @NotBlank(message="token 을 입력해주세요.")
    private String token;
}

