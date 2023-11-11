package gwangjang.server.domain.member.adapter.consumer.web.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private String memberId;
    private String nickname;
    private String profileImage;

}
