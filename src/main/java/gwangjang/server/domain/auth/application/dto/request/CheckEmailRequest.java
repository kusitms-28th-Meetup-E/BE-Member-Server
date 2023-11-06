package gwangjang.server.domain.auth.application.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CheckEmailRequest {
    String email;
    String code;
}
