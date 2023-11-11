package gwangjang.server.domain.member.presentation;

import gwangjang.server.domain.member.adapter.consumer.web.dto.post.MemberDto;
import gwangjang.server.domain.member.feign.MemberFeignUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/feign")
public class MemberFeignController {

    private final MemberFeignUseCase memberFeignUseCase;

    @GetMapping("/{socialId}")
    public MemberDto getMember(@PathVariable("socialId") String socialId) {
        return memberFeignUseCase.getMemberDto(socialId);
    }



}
