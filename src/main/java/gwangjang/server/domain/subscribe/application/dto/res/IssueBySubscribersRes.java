package gwangjang.server.domain.subscribe.application.dto.res;

import gwangjang.server.global.feign.dto.response.IssueDto;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueBySubscribersRes {

    private Long categoryId;
    private String category; //영역
    private Long titleId;
    private String title; //주제
    private Long subscribeCount;
    private String imgUrl;

    public IssueBySubscribersRes(Long titleId, Long subscribeCount) {
        this.titleId = titleId;
        this.subscribeCount = subscribeCount;
    }

    public void updateByIssueDto(IssueDto issueDto) {
        this.title = issueDto.getIssueTitle();
        this.categoryId = issueDto.getTopicId();
        this.category = issueDto.getTopicTitle();
        this.imgUrl = issueDto.getImgUrl();
    }
}
