package gwangjang.server.domain.subscribe.application.dto.res;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueSubscribeInfoRes {
    private Long subscribers;

    public void updateSubscribers(Long subscribers) {
        this.subscribers = subscribers;
    }

}
