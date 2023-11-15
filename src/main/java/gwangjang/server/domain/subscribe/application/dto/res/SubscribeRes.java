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

    private String topic;
    private String issue;
    private boolean status;


    public SubscribeRes setUnScribe() {
        this.status = Boolean.FALSE;
        return this;
    }
    public SubscribeRes setSubscribe() {
        this.status = Boolean.TRUE;
        return this;
    }

}
