package com.fengwenyi.example.miaosha.redis;

/**
 * @author Wenyi Feng
 * @since 2018-12-04
 */
public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();

}
