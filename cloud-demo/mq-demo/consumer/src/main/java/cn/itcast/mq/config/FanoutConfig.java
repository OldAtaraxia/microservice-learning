package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Queue fanoutQueueA() {
        return new Queue("fanoutQueueA");
    }

    // 绑定队列1到交换机
    @Bean
    public Binding fanoutBindingA(Queue fanoutQueueA, FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(fanoutQueueA)
                .to(fanoutExchange);
    }

    @Bean
    public Queue fanoutQueueB() {
        return new Queue("fanoutQueueB");
    }

    @Bean
    public Binding fanoutBindingB(Queue fanoutQueueB, FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(fanoutQueueB)
                .to(fanoutExchange);
    }
}
