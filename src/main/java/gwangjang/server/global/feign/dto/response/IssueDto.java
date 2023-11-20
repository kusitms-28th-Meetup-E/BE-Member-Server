package gwangjang.server.global.feign.dto.response;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueDto {
    private String issueTitle;
    private String topicTitle;
    private String imgUrl;
    private Long issueId;
    private Long topicId;
}
