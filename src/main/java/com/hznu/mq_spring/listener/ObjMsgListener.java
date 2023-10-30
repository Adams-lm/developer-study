package com.hznu.mq_spring.listener;

import com.hznu.mq_spring.entity.Order;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author LIN
 * @date 2023/8/7 16:02
 */
@Component
@RocketMQMessageListener(topic = "powernode-obj", consumerGroup = "powernode-obj-group")
public class ObjMsgListener implements RocketMQListener<Order> {

    @Override
    public void onMessage(Order message) {
        System.out.println(message);
    }
}
