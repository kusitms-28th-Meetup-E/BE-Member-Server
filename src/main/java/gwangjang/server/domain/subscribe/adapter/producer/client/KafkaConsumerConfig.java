package gwangjang.server.domain.subscribe.adapter.producer.client;//package gwangjang.server.domain.adapter.out.client;
//
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableKafka
//@Configuration
//public class KafkaConsumerConfig {
//
//    Environment env;
//
//    @Autowired
//    public KafkaConsumerConfig(Environment env) {
//        this.env = env;
//    }
//
//
//    @Bean
//    // Topic에 접속에 필요한 정보
//    public ConsumerFactory<String, String> consumerFactory() {
//        Map<String, Object> properties = new HashMap<>();
//        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("gugbab.public.url.local.kafka_server"));
//        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "serviesConsumerGroupId");
//        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(properties);
//    }
//
//    @Bean
//    // Topic Listener
//    public ConcurrentKafkaListenerContainerFactory<String, String> KafkaListenerContatinerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
//        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
//
//        return kafkaListenerContainerFactory;
//    }
//
//
//}