//package gwangjang.server.global.feign.client;
//
//import gwangjang.server.global.feign.dto.response.IssueDto;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@FeignClient(name="KEYWORDSERVICE")
//public interface FindKeywordFeignClient {
//
//    @GetMapping("/feign/{issueId}")
//    ResponseEntity<IssueDto> getIssueByIssueId(@PathVariable("issueId") Long issueId);
//
//}
