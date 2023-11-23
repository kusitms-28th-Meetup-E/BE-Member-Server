package gwangjang.server.domain.subscribe.adapter.producer.web.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribersByIssueDto {
    
    private List<SubscribeData> data;
}




