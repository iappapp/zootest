package com.github.iappapp.dao.domain;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String host;
    private String user;

    public User() {

    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user1 = (User) o;
        return Objects.equals(host, user1.host) &&
                Objects.equals(user, user1.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(host, user);
    }

    @Override
    public String toString() {
        return "User{" +
                "host='" + host + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
