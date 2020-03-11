package com.github.iappapp.shiro.session;

import com.github.iappapp.util.RedisUtil;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Slf4j
public class RedisSessionDao extends AbstractSessionDAO {
    private final static String SHIRO_SESSION_PREDIX = "shiro-session";

    private RedisUtil redisUtil;

    public RedisUtil getRedisUtil() {
        return redisUtil;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    private byte[] getKey(String key) {
        // charset issue
        return (SHIRO_SESSION_PREDIX + key).getBytes();
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable serializable = generateSessionId(session);
        saveSession(session);
        return serializable;
    }

    private void saveSession(Session session) {
        if (null != session && session.getId() != null) {
            byte[] key = this.getKey(session.getId().toString());
            byte[] value = SerializationUtils.serialize(session);
            redisUtil.set(new String(key), new String(value));
            // expire
            redisUtil.expire(key, 10 * 60);
        }
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        if (serializable == null) {
            return null;
        }

        byte[] key = getKey(serializable.toString());
        byte[] value = redisUtil.get(key);

        return (Session) SerializationUtils.deserialize(value);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            return;
        }

        byte[] key = getKey(session.getId().toString());
        redisUtil.del(new String(key));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<byte[]> keys = redisUtil.keys(SHIRO_SESSION_PREDIX.getBytes());
        Set<Session> sessions = Sets.newHashSet();

        if (CollectionUtils.isEmpty(keys)) {
            return sessions;
        }

        for (byte[] key : keys) {
            Session session = (Session) SerializationUtils.deserialize(redisUtil.get(key));
            sessions.add(session);
        }

        return sessions;
    }
}
