package com.yangy.common.rabbitmq.consumer;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.yangy.common.rabbitmq.RabbitMQMessageVO;
import com.yangy.common.rabbitmq.RabbitMQService;
import com.yangy.common.rabbitmq.enums.RabbitMQConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>
 * 死信转发处理
 * </p>
 *
 * @author yangy
 * @create 2018/7/4
 * @since 1.0.0
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitMQConstants.DEAD_TRANSFER_QUEUE)
public class DeadTransferProcessor {

    @Autowired
    RabbitMQService rabbitMQService;

    @RabbitHandler
    public void process(@Payload RabbitMQMessageVO mqMessageVO, Message message, Channel channel) {
        log.info("MQ:转发队列消费者 收到的消息为 info={}", JSON.toJSONString(mqMessageVO));

        Object model = mqMessageVO.getModel();
        String queueName = mqMessageVO.getQueueName();
        //向指定的队列中发送消息
        rabbitMQService.send(model, queueName, RabbitMQConstants.EXCHANGE);

        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}