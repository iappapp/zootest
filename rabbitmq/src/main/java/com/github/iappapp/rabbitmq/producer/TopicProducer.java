package com.github.iappapp.rabbitmq.producer;

import com.github.iappapp.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TopicProducer {
    private Channel channel;

    private String exchangeName;

    private String queueName;

    private String routeKey;

    public TopicProducer(Channel channel, String exchangeName, String queueName, String routeKey) {
        this.channel = channel;
        this.exchangeName = exchangeName;
        this.queueName = queueName;
        this.routeKey = routeKey;
    }

    public void init() throws Exception {
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
    }

    public void sendMessage(String message) throws Exception {
        channel.basicPublish(exchangeName, routeKey, null, message.getBytes());
    }

    public static void main(String[] args) throws Exception {
        Channel channel = new ConnectionUtils().initChannel("guest", "guest", "localhost", 5672);
        TopicProducer topicProducer = new TopicProducer(channel, "loan", "loan", "subLoan");
        topicProducer.init();
        topicProducer.sendMessage("{\"id\":\"1\"}");
    }
}
