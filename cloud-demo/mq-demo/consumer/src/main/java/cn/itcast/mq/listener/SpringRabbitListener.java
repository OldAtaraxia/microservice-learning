package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class SpringRabbitListener {

    @RabbitListener(queues = "fanoutQueueA")
    public void listenWorkQueue(String msg) throws InterruptedException {
        System.out.println("消费者1接收到fanoutQueueA的消息：" + msg + " " + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "fanoutQueueB")
    public void listenWorkQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2接收到fanoutQueueB的消息：" + msg+ " " + LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "directQueue1"),
            exchange = @Exchange(name = "directExchange", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void listenDirectQueueA(String msg) {
        System.out.println("消费者1接收到directQueueA的消息：" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "directQueue2"),
            exchange = @Exchange(name = "directExchange", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}
    ))
    public void listenDirectQueueB(String msg) {
        System.out.println("消费者2接收到directQueueA的消息：" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("topicQueueA"),
            exchange = @Exchange(name = "topicExchange", type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void listenTopicQueueA(String msg) {
        System.out.println("消费者1接收到topicQueueA的消息：" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("topicQueueB"),
            exchange = @Exchange(name = "topicExchange", type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenTopicQueueB(String msg) {
        System.out.println("消费者2接收到topicQueueB的消息：" + msg);
    }

}
