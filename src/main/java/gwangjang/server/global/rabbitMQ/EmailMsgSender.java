package gwangjang.server.global.rabbitMQ;

import gwangjang.server.domain.subscribe.adapter.producer.web.dto.SubscribeData;
import gwangjang.server.domain.subscribe.domain.service.SubscribeQueryService;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailMsgSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private SubscribeQueryService subscribeQueryService;

    @Autowired
    private TopicExchange topic;

    public void sendEmail(String routingKey, Email email){
        rabbitTemplate.convertAndSend(topic.getName(), routingKey, email);
    }
    public void sendSubscribeData(String routingKey) {
        List<SubscribeData> subscribeDataList = subscribeQueryService.getIssueBySubscribers();
        SubscribeDataMessage message = new SubscribeDataMessage(subscribeDataList);
        rabbitTemplate.convertAndSend(topic.getName(), routingKey, message);
    }
}