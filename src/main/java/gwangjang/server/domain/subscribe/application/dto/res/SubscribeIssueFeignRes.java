package gwangjang.server.domain.subscribe.application.dto.res;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeIssueFeignRes {

    private String issue;
    private Long issueId;

    public SubscribeIssueFeignRes(Long issueId) {
        this.issueId = issueId;
    }

    public void updateIssue(String issue) {
        this.issue = issue;
    }
}
