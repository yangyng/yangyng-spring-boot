package com.yangy.common.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * rabbit消息对象
 *
 * @author yang yang
 * @create 2018/10/12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RabbitMQMessageVO {

    private Long delayTime;
    private Object model;
    private String queueName;
    private String bindKey;

}