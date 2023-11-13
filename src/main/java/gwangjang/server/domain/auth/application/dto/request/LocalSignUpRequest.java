package gwangjang.server.domain.auth.application.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalSignUpRequest {
    private String id;
    private String pw;
    private String nickname;
    private String gender;
    private String email;
    private String birthDate;
}
