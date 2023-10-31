package gwangjang.server.domain.auth.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocalSignInRequest {
    private String id;
    private String pw;
}
