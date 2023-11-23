package gwangjang.server.domain.subscribe.adapter.producer.client;//package gwangjang.server.domain.adapter.out.client;
//
//import gwangjang.server.domain.domain.repository.CommunityRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class KafkaConsumer {
//
//    CommunityRepository communityRepository;
//
//    @Autowired
//    public KafkaConsumer(CommunityRepository communityRepository) {
//        this.communityRepository = communityRepository;
//    }
//
//    @KafkaListener(topics = "gugbab-services-kafka-users-star-cnt-topic", groupId = "serviesConsumerGroupId")
//    public void updateUserStarCnt(String kafkaMessage) {
//
//        log.info("kafka Message : " + kafkaMessage);
//
//
//    }
//
//}
