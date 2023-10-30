package com.hznu.mq.producer;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.hznu.mq.entity.Order;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.protocol.heartbeat.MessageModel;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author LIN
 * @date 2023/8/7 14:49
 */
public class Producer {

    @Test
    public void testProducer() throws Exception {
        // 创建默认的生产者
        DefaultMQProducer producer = new DefaultMQProducer("test-group");
        // 设置nameServer地址
        producer.setNamesrvAddr("38.207.176.91:9876");
        // 启动实例
        producer.start();
        for (int i = 0; i < 1; i++) {
            // 创建消息
            // 第一个参数：主题的名字
            // 第二个参数：消息内容
            Message msg = new Message("TestTopic", ("Hello RocketMQ " + i).getBytes());
            SendResult send = producer.send(msg);
            System.out.println(send);
        }
        // 关闭实例
        producer.shutdown();
    }

    @Test
    public void testAsyncProducer() throws Exception {
        // 创建默认的生产者
        DefaultMQProducer producer = new DefaultMQProducer("test-group");
        // 设置nameServer地址
        producer.setNamesrvAddr("38.207.176.91:9876");
        // 启动实例
        producer.start();
        Message msg = new Message("TestTopic", ("异步消息").getBytes());
        producer.send(msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("发送成功");
            }
            @Override
            public void onException(Throwable e) {
                System.out.println("发送失败");
            }
        });
        System.out.println("看看谁先执行");
        // 挂起jvm 因为回调是异步的不然测试不出来
        System.in.read();
        // 关闭实例
        producer.shutdown();
    }

    @Test
    public void testOnewayProducer() throws Exception {
        // 创建默认的生产者
        DefaultMQProducer producer = new DefaultMQProducer("test-group");
        // 设置nameServer地址
        producer.setNamesrvAddr("38.207.176.91:9876");
        // 启动实例
        producer.start();
        Message msg = new Message("TestTopic", ("单向消息").getBytes());
        // 发送单向消息
        producer.sendOneway(msg);
        // 关闭实例
        producer.shutdown();
    }

    @Test
    public void testDelayProducer() throws Exception {
        // 创建默认的生产者
        DefaultMQProducer producer = new DefaultMQProducer("test-group");
        // 设置nameServer地址
        producer.setNamesrvAddr("38.207.176.91:9876");
        // 启动实例
        producer.start();
        Message msg = new Message("TestTopic", ("延迟消息").getBytes());
        // 给这个消息设定一个延迟等级
        // messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
        msg.setDelayTimeLevel(3);
        // 发送单向消息
        producer.send(msg);
        // 打印时间
        System.out.println(new Date());
        // 关闭实例
        producer.shutdown();
    }

    @Test
    public void testOrderlyProducer() throws Exception {
        // 创建默认的生产者
        DefaultMQProducer producer = new DefaultMQProducer("test-group");
        // 设置nameServer地址
        producer.setNamesrvAddr("38.207.176.91:9876");
        // 启动实例
        producer.start();
        List<Order> orderList = Arrays.asList(
                new Order(1, 111, 59D, new Date(), "下订单"),
                new Order(2, 111, 59D, new Date(), "物流"),
                new Order(3, 111, 59D, new Date(), "签收"),
                new Order(4, 112, 89D, new Date(), "下订单"),
                new Order(5, 112, 89D, new Date(), "物流"),
                new Order(6, 112, 89D, new Date(), "拒收")
        );
        // 循环集合开始发送
        orderList.forEach(order -> {
            Message message = new Message("TestTopic", order.toString().getBytes());
            try {
                // 发送的时候 相同的订单号选择同一个队列
                producer.send(message, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        // 当前主题有多少个队列
                        int queueNumber = mqs.size();
                        // 这个arg就是后面传入的 order.getOrderNumber()
                        Integer i = (Integer) arg;
                        // 用这个值去%队列的个数得到一个队列
                        int index = i % queueNumber;
                        // 返回选择的这个队列即可 ，那么相同的订单号 就会被放在相同的队列里 实现FIFO了
                        return mqs.get(index);
                    }
                }, order.getOrderNumber());
            } catch (Exception e) {
                System.out.println("发送异常");
            }
        });
        // 关闭实例
        producer.shutdown();
    }

    @Test
    public void testBatchProducer() throws Exception {
        // 创建默认的生产者
        DefaultMQProducer producer = new DefaultMQProducer("test-group");
        // 设置nameServer地址
        producer.setNamesrvAddr("38.207.176.91:9876");
        // 启动实例
        producer.start();
        List<Message> msgs = Arrays.asList(
                new Message("TestTopic", "我是一组消息的A消息".getBytes()),
                new Message("TestTopic", "我是一组消息的B消息".getBytes()),
                new Message("TestTopic", "我是一组消息的C消息".getBytes())

        );
        SendResult send = producer.send(msgs);
        System.out.println(send);
        // 关闭实例
        producer.shutdown();
    }

    @Test
    public void testTagProducer() throws Exception {
        // 创建默认的生产者
        DefaultMQProducer producer = new DefaultMQProducer("test-group");
        // 设置nameServer地址
        producer.setNamesrvAddr("38.207.176.91:9876");
        // 启动实例
        producer.start();
        Message msg = new Message("TestTopic","tagA", "我是一个带标记的消息".getBytes());
        //Message msg = new Message("TestTopic","tagD", "我是一个带标记的消息".getBytes());
        SendResult send = producer.send(msg);
        System.out.println(send);
        // 关闭实例
        producer.shutdown();
    }

    @Test
    public void testTagConsumer() throws Exception {
        // 创建默认消费者组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer-group");
        // 设置nameServer地址
        consumer.setNamesrvAddr("38.207.176.91:9876");
        // 订阅一个主题来消费   表达式，默认是*,支持"tagA || tagB || tagC" 这样或者的写法 只要是符合任何一个标签都可以消费
        consumer.subscribe("TestTopic", "tagA || tagB || tagC");
        // 注册一个消费监听 MessageListenerConcurrently是并发消费
        // 默认是20个线程一起消费，可以参看 consumer.setConsumeThreadMax()
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                // 这里执行消费的代码 默认是多线程消费
                System.out.println(Thread.currentThread().getName() + "----" + new String(msgs.get(0).getBody()));
                System.out.println(msgs.get(0).getTags());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.in.read();
    }

    @Test
    public void testDeadMsgProducer() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("dead-group");
        producer.setNamesrvAddr("38.207.176.91:9876");
        producer.start();
        Message message = new Message("dead-topic", "我是一个死信消息".getBytes());
        producer.send(message);
        producer.shutdown();
    }

    @Test
    public void testRepeatProducer() throws Exception {
        // 创建默认的生产者
        DefaultMQProducer producer = new DefaultMQProducer("test-group");
        // 设置nameServer地址
        producer.setNamesrvAddr("38.207.176.91:9876");
        // 启动实例
        producer.start();
        // 我们可以使用自定义key当做唯一标识
        String keyId = UUID.randomUUID().toString();
        System.out.println(keyId);
        Message msg = new Message("TestTopic", "tagA", keyId, "我是一个测试消息".getBytes());
        SendResult send = producer.send(msg);
        System.out.println(send);
        // 关闭实例
        producer.shutdown();
    }

    /**
     * 在boot项目中可以使用@Bean在整个容器中放置一个单例对象
     */

    private static BloomFilter<String> bloomFilter = BloomFilter
            .create(Funnels.stringFunnel(StandardCharsets.UTF_8), 100,0.01);

    @Test
    public void testRepeatConsumer() throws Exception {
        // 创建默认消费者组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer-group");
        consumer.setMessageModel(MessageModel.BROADCASTING);
        // 设置nameServer地址
        consumer.setNamesrvAddr("38.207.176.91:9876");
        // 订阅一个主题来消费   表达式，默认是*
        consumer.subscribe("TestTopic", "*");
        // 注册一个消费监听 MessageListenerConcurrently是并发消费
        // 默认是20个线程一起消费，可以参看 consumer.setConsumeThreadMax()
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                // 拿到消息的key
                MessageExt messageExt = msgs.get(0);
                String keys = messageExt.getKeys();
                System.out.println(keys);
                // 判断是否存在布隆过滤器中
                if (bloomFilter.mightContain(keys)) {
                    // 直接返回了 不往下处理业务
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                // 这个处理业务，然后放入过滤器中
                // do sth...
                bloomFilter.put(keys);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.in.read();
    }


}
