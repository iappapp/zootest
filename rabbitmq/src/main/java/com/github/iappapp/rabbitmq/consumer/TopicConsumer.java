package com.github.iappapp.rabbitmq.consumer;

import com.github.iappapp.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.*;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor
public class TopicConsumer {
    private Channel channel;

    private String exchangeName;

    private String queueName;

    private String routeKey;

    public TopicConsumer(Channel channel, String exchangeName, String queueName, String routeKey) {
        this.channel = channel;
        this.exchangeName = exchangeName;
        this.queueName = queueName;
        this.routeKey = routeKey;
    }

    public void consume(DefaultConsumer consumer) throws Exception {
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        channel.basicConsume(queueName, false, consumer);
    }

    public static void main(String[] args) throws Exception {
        Channel channel = new ConnectionUtils().initChannel("guest", "guest", "localhost", 5672);
        TopicConsumer topicConsumer = new TopicConsumer(channel, "loan", "loan", "subLoan");
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routingKey = envelope.getRoutingKey();
                String contentType = properties.getContentType();

                long deliveryTag = envelope.getDeliveryTag();

                String bodyStr = new String(body, "UTF-8");
                System.out.println(bodyStr);
                //确认消息
                channel.basicAck(deliveryTag, false);
            }
        };
        topicConsumer.consume(consumer);
    }
}
