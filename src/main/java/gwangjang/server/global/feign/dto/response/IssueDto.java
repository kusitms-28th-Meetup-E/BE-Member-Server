package gwangjang.server.global.feign.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssueDto {
    private String topic;
    private String issue;
    private String imgUrl;
}
