package gwangjang.server.domain.subscribe.adapter.producer.web.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribersByIssueDto {
    private Long issueId;
    private Long count;
}
