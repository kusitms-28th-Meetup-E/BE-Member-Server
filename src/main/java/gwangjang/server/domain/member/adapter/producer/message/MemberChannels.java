package gwangjang.server.domain.member.adapter.producer.message;
import gwangjang.server.domain.member.adapter.consumer.web.dto.post.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Supplier;

@Slf4j
@Configuration
public class MemberChannels {

    @Bean
    public Supplier<Message<MemberDto>> sendToCommunity() {  // 메시지를 주기적으로 생성하고 보내는 데 사용
        return () -> {

            log.info("send To Community !!!!");
            // 메시지 생성 로직
            MemberDto memberInfo = new MemberDto("LOCAL-323","nickname", "https://img");
            // dto to string
            return MessageBuilder.withPayload(memberInfo).build();
        };
    }

}
