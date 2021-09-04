package com.github.iappapp.rabbitmq.consumer;

import com.github.iappapp.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class DirectConsumer {
    private Channel channel;
    private String exchangeName;
    private String routeKey;
    private DefaultConsumer consumer;

    public DirectConsumer(Channel channel, String exchangeName, String routeKey) {
        this.channel = channel;
        this.exchangeName = exchangeName;
        this.routeKey = routeKey;
    }

    public void setConsumer(DefaultConsumer consumer) {
        this.consumer = consumer;
    }

    public void consumeMessage() throws Exception {
        channel.exchangeDeclare(exchangeName, "direct", true);
        //声明队列
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, exchangeName, routeKey);

        while(true) {
            //消费消息
            boolean autoAck = false;
            String consumerTag = "";
            channel.basicConsume(queueName, autoAck, consumerTag, consumer);
        }
    }

    public static void main(String[] args) throws Exception {
        Channel channel = new ConnectionUtils().initChannel("guest", "guest", "localhost", 5672);
        DirectConsumer consumer = new DirectConsumer(channel, "message", "subMessage");

        //声明交换器
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String routingKey = envelope.getRoutingKey();
                String contentType = properties.getContentType();

                long deliveryTag = envelope.getDeliveryTag();

                String bodyStr = new String(body, "UTF-8");
                System.out.println(bodyStr);
                //确认消息
                channel.basicAck(deliveryTag, false);
            }
        };
        consumer.setConsumer(defaultConsumer);
        consumer.consumeMessage();
    }
}
