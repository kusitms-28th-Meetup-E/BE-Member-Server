package gwangjang.server.domain.member.adapter.consumer.message;
import gwangjang.server.domain.auth.application.mapper.MemberMapper;
import gwangjang.server.global.feign.dto.response.MemberDto;
import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class CommunityChannels {

    private final MemberQueryService memberQueryService;
    private final MemberMapper memberMapper;

//    @Bean
//    public Function<String, String> uppercase() { // 메시지를 받아 처리한 후 결과를 반환
//    return message -> {
//        // 메시지 처리 로직
//        return message.toUpperCase();
//    };
//}
//    @Bean
//    public Consumer<String> receiveFromCommunity(Long memberId) { //메시지를 받아 처리하는 데 사용되지만 반환 값이 없
//        return message -> {
//            // 메시지 처리 로직 ( 역직렬화 )
//            System.out.println("Member Received: " + message);
//        };
//    }

    @Bean
    public Function<String, MemberDto> receiveFromCommunity() {
        return memberId -> {
            // memberId를 사용하여 Member 객체를 조회
            Member member = memberQueryService.getMemberBySocialId(memberId);

            return memberMapper.mapToMemberDto(member);

        };
    }
}
