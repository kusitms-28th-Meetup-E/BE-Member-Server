//package gwangjang.server.domain.subscribe.adapter.producer.message;
//import gwangjang.server.domain.subscribe.adapter.producer.web.dto.SubscribeData;
//import gwangjang.server.domain.subscribe.adapter.producer.web.dto.SubscribersByIssueDto;
//import gwangjang.server.domain.subscribe.application.service.SubscribeReadUseCase;
//import gwangjang.server.global.feign.dto.response.MemberDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//
//import java.util.List;
//import java.util.function.Supplier;
//
//@Slf4j
//@Configuration
//@RequiredArgsConstructor
//public class SubscribeChannels {
//
//    private final SubscribeReadUseCase subscribeReadUseCase;
//
//    @Bean
//    public Supplier<Message<List<SubscribeData>>> sendToKeyword() {  // 메시지를 주기적으로 생성하고 보내는 데 사용
//        return () -> {
//
//            log.info("send To Keyword !!!!");
//
//
//            List<SubscribeData> allIssueBySubscribers = subscribeReadUseCase.getAllIssueBySubscribers();
//            // 메시지 생성 로직
////            MemberDto memberInfo = new MemberDto("LOCAL-323","nickname", "https://img");
//            // dto to string
//            return MessageBuilder.withPayload(allIssueBySubscribers).build();
//        };
//    }
//
//}
