package com.fengwenyi.example.miaosha.redis;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * @author Wenyi Feng
 * @since 2018-12-04
 */
@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;


    /**
     * 获取数据
     * @param prefix 前缀
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            // 真正的key
            String realKey = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            return stringToBean(str, clazz);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 设置对象
     * @param prefix 前缀
     * @param key
     * @param obj
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix prefix, String key, T obj) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(obj);
            if (StringUtils.isEmpty(str)) {
                return false;
            }
            // 生成真正的key
            String realKey = prefix.getPrefix() + key;
            int sevonds = prefix.expireSeconds();
            if (sevonds <= 0) {
                jedis.set(realKey, str);
            } else {
                jedis.setex(realKey, sevonds, str);
            }
            return true;
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * 判断key是否存在
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 增加值
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 减少值
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    public boolean delete() {
        return false;
    }

    public List<String> scanKeys(String key) {
        return null;
    }


    public <T> T stringToBean(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }

        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        }
        if (clazz == String.class) {
            return (T) str;
        }
        if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        }
        return new Gson().fromJson(str, clazz);
    }

    public <T> String beanToString(T obj) {
        if (obj == null) {
            return null;
        }
        Class<?> clazz = obj.getClass();
        if (clazz == int.class || clazz == Integer.class
                || clazz == String.class
                || clazz == long.class || clazz == Long.class) {
            return obj.toString();
        }
        return new Gson().toJson(obj);
    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
