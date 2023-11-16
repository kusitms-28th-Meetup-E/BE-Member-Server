package gwangjang.server.domain.subscribe.application.dto.res;

import gwangjang.server.global.feign.dto.response.IssueDto;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeMyPageRes {

    private Long issueId;
    private String topic;
    private String issue;
    private String imgUrl;

    public SubscribeMyPageRes(Long issueId) {
        this.issueId = issueId;
    }

    public void updateNames(IssueDto issueDto) {
        this.topic = issueDto.getTopic();
        this.issue = issueDto.getIssue();
        this.imgUrl = issueDto.getImgUrl();
    }
}
