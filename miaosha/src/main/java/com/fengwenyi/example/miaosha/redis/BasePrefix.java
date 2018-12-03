package com.fengwenyi.example.miaosha.redis;

import com.google.gson.Gson;
import org.springframework.util.StringUtils;

/**
 * @author Wenyi Feng
 * @since 2018-12-04
 */
public abstract class BasePrefix implements KeyPrefix {

    private int expireSeconds;
    private String prefix;

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    public BasePrefix(String prefix) {
        this(0, prefix); // 0 代表永不过期
    }

    public int expireSeconds() { // 0 代表永不过期
        return expireSeconds;
    }

    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
