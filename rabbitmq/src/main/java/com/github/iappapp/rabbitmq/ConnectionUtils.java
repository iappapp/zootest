package com.github.iappapp.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtils {
    private Connection connection;

    private Channel channel;

    public Channel initChannel(String userName, String password, String host, int port) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(userName);
        factory.setPassword(password);
        factory.setHost(host);
        factory.setPort(port);

        connection = factory.newConnection();
        channel = connection.createChannel();
        return channel;
    }

    public void close() {
        try {
            if (null != channel) {
                channel.close();
            }
            if (null != connection) {
                connection.close();
            }
        } catch (Exception ex) {

        }
    }
}
