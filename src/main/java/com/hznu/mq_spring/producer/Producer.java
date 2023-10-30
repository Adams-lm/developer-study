package com.hznu.mq_spring.producer;

import com.hznu.mq_spring.entity.Order;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

/**
 * @author LIN
 * @date 2023/8/7 14:49
 */
public class Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    @Test
    public void testObjectMsg() throws Exception {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderName("我的订单");
        order.setPrice(998D);
        order.setCreateTime(new Date());
        order.setDesc("加急配送");
        // 往powernode-obj主题发送一个订单对象
        rocketMQTemplate.syncSend("powernode-obj", order);
    }
}
