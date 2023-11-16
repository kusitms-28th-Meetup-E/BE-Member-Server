package gwangjang.server.global.feign.dto.response;

import gwangjang.server.domain.subscribe.application.dto.res.SubscribeMyPageRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSubscribeDto {

    // member to contents
    private String nickname;
    private String profileImg;
    private List<Long> subscribeList;
}
