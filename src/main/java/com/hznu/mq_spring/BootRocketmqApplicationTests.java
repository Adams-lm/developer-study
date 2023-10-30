package com.hznu.mq_spring;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author LIN
 * @date 2023/8/7 15:53
 */
@SpringBootTest
class BootRocketmqApplicationTests {



            /**
             * 注入rocketMQTemplate，我们使用它来操作mq
             */
            @Autowired
            private RocketMQTemplate rocketMQTemplate;

            @Test
            public void testSimpleMsg () throws Exception {
                // 往powernode的主题里面发送一个简单的字符串消息
                SendResult sendResult = rocketMQTemplate.syncSend("powernode", "我是一个简单的消息");
                // 拿到消息的发送状态
                System.out.println(sendResult.getSendStatus());
                // 拿到消息id
                System.out.println(sendResult.getMsgId());
            }

            @Test
            public void testAsyncSend () throws Exception {
                // 发送异步消息，发送完以后会有一个异步通知
                rocketMQTemplate.asyncSend("powernode", "发送一个异步消息", new SendCallback() {
                    /**
                     * 成功的回调
                     * @param sendResult
                     */
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println("发送成功" + sendResult);
                    }

                    /**
                     * 失败的回调
                     * @param throwable
                     */
                    @Override
                    public void onException(Throwable throwable) {
                        System.out.println("发送失败" + throwable);
                    }
                });
                // 测试一下异步的效果
                System.out.println("谁先执行");
                // 挂起jvm 不让方法结束
                System.in.read();
            }

            /**
             * 测试单向消息
             *
             * @throws Exception
             */
            @Test
            public void testOnWay () throws Exception {
                // 发送单向消息，没有返回值和结果
                rocketMQTemplate.sendOneWay("powernode", "这是一个单向消息");
            }

            /**
             * 测试延迟消息
             *
             * @throws Exception
             */
            @Test
            public void testDelay () throws Exception {
                // 构建消息对象, 下面必须传Message类型
                Message<String> message = MessageBuilder.withPayload("我是一个延迟消息").build();
                // 发送一个延时消息，延迟等级为4级，也就是30s后被监听消费
                SendResult sendResult = rocketMQTemplate.syncSend("powernode", message, 2000, 4);
                System.out.println(sendResult.getSendStatus());
            }
        }
