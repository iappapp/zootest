package com.github.iappapp.rabbitmq.producer;

import com.github.iappapp.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;

public class DirectProducer {
    private Channel channel;
    private String exchangeName;
    private String routeKey;

    public DirectProducer() {
    }

    public DirectProducer(Channel channel, String exchangeName, String routeKey) throws Exception {
        this.channel = channel;
        this.exchangeName = exchangeName;
        this.routeKey = routeKey;
        channel.exchangeDeclare(exchangeName, "direct", true);
    }


    public void sendMessage(String message) throws Exception {
        channel.basicPublish(exchangeName, routeKey, null, message.getBytes());
    }

    public static void main(String[] args) throws Exception {
        Channel channel = new ConnectionUtils().initChannel("guest", "guest", "localhost", 5672);
        DirectProducer producer = new DirectProducer(channel, "message", "subMessage");
        for (int i = 0; i < 1000000; i++) {
            producer.sendMessage("{}");
        }
    }
}
