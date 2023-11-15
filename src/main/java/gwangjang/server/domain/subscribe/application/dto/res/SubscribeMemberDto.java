package gwangjang.server.domain.subscribe.application.dto.res;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeMemberDto {
    private String nickname;
    private String profileImg;
    private List<SubscribeRes> subscribeResList;
}
