package gwangjang.server.global.rabbitMQ;


import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "amq.topic", type = "topic", durable = "true"), //
            key = "test"))
    public void handleMsg1() {
        System.out.println("test");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "kkaok", durable = "true"),
            exchange = @Exchange(value = "amq.topic", type = "topic", durable = "true"), //
            key = "test.0001"))
    public void handleMsg2() {
        System.out.println("test");
    }

}
