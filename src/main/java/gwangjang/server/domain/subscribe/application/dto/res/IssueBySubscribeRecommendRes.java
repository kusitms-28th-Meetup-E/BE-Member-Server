package gwangjang.server.domain.subscribe.application.dto.res;

import gwangjang.server.global.feign.dto.response.IssueDto;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueBySubscribeRecommendRes {

    private Long issueId;
    private String title;
    private Long categoryId;
    private String category;
    private Long subscribers;
    private String imgUrl;

    public void updateSubscribers(Long subscribers) {
        this.subscribers = subscribers;
    }

    public void updateIssueInfo(IssueDto issueDto) {
        this.title = issueDto.getIssueTitle();
        this.categoryId = issueDto.getTopicId();
        this.category = issueDto.getTopicTitle();
        this.imgUrl = issueDto.getImgUrl();
    }


}
