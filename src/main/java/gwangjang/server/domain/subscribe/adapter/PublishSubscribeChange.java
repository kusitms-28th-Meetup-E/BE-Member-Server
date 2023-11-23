//package gwangjang.server.domain.subscribe.adapter;
//
//import gwangjang.server.domain.subscribe.adapter.producer.web.dto.SubscribeData;
//import gwangjang.server.domain.subscribe.adapter.producer.web.dto.SubscribersByIssueDto;
//import gwangjang.server.domain.subscribe.application.service.SubscribeReadUseCase;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.stream.messaging.Source;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class PublishSubscribeChange {
//
//    @Autowired
//    private Source source;
//    @Autowired
//    private SubscribeReadUseCase subscribeReadUseCase;
//
//    public void publishMemberChange() {
//        List<SubscribeData> allIssueBySubscribers = subscribeReadUseCase.getAllIssueBySubscribers();
//
//        source.output().send(MessageBuilder.withPayload(allIssueBySubscribers).build());
//    }
//}
