//package gwangjang.server.domain.member.application.service;
//
//import gwangjang.server.domain.member.adapter.consumer.message.CommunityChannels;
//import gwangjang.server.domain.member.adapter.producer.message.MemberChannels;
//import gwangjang.server.global.annotation.DomainService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.stream.function.StreamBridge;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MessageWithCommunityUseCase {
//
//    @Autowired
//    private StreamBridge streamBridge;
//
//    private static final String COMMUNITY_TO_MEMBER = "receiveFromMember-in-0";
//
//    public void sendToCommunity(String memberId) {
//        streamBridge.send(COMMUNITY_TO_MEMBER, memberId);
//    }
//
//
//
//
//}
