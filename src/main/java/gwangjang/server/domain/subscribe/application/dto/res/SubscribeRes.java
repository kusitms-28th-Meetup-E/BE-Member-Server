package gwangjang.server.domain.subscribe.application.dto.res;

import gwangjang.server.domain.member.domain.entity.Member;
import lombok.*;

import java.util.List;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeRes {

    private Long issueId;
    private boolean status;
    private Long subscribers;


    public SubscribeRes setUnScribe(Long subscribers) {
        this.status = Boolean.FALSE;
        this.subscribers = subscribers;
        return this;
    }
    public SubscribeRes setSubscribe(Long subscribers) {
        this.status = Boolean.TRUE;
        this.subscribers = subscribers;
        return this;
    }

}
