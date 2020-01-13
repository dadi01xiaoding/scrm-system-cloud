package com.dadi01.scrm.foundation.cache;


import redis.clients.jedis.JedisCluster;

/**
 * @Description TODO
 * @Author fang
 * @Date 2019-11-10 18:00
 **/
public interface IRedisClusterConsumer<T> {
    T handle(JedisCluster jedisCluster) throws Exception;
}
