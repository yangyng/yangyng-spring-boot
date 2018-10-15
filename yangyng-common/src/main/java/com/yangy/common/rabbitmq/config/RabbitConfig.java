package com.yangy.common.rabbitmq.config;

import com.yangy.common.rabbitmq.enums.RabbitMQConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * rabbitmq 配置
 *
 * @author yangy
 * @create 2018/7/4
 * @since 1.0.0
 */
@Configuration
public class RabbitConfig {

    //死信队列
    @Bean
    public Queue deadQueue() {
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("x-max-length", 10000);
        arguments.put("x-dead-letter-exchange", RabbitMQConstants.EXCHANGE);
        arguments.put("x-dead-letter-routing-key", RabbitMQConstants.DEAD_QUEUE);
        return new Queue(RabbitMQConstants.DEAD_QUEUE, true, false, false, arguments);
    }

    //死信转发队列
    @Bean
    public Queue deadTransferQueue() {
        return new Queue(RabbitMQConstants.DEAD_TRANSFER_QUEUE, true, false, false);
    }

    //默认direct交换机 routingKey完全相等
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(RabbitMQConstants.EXCHANGE, true, false);
    }

    //死信转发队列路由键与交换机绑定
    @Bean
    public Binding deadTransferBinding() {
        return BindingBuilder.bind(deadTransferQueue()).to(directExchange()).with(RabbitMQConstants.DEAD_TRANSFER_QUEUE);
    }

    //死信队列路由键与交换机绑定
    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadQueue()).to(directExchange()).with(RabbitMQConstants.DEAD_QUEUE);
    }
}