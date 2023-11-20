package gwangjang.server.domain;

import gwangjang.server.global.feign.dto.response.IssueDto;
import gwangjang.server.global.response.SuccessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="KEYWORDSERVICE")
public interface FindKeywordFeignClient {

    @GetMapping("/issue/{issueId}")
    ResponseEntity<SuccessResponse<IssueDto>> getIssueByIssueId(@PathVariable("issueId") Long issueId);

}
