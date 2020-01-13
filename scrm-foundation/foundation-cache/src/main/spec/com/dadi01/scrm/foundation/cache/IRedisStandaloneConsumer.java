package com.dadi01.scrm.foundation.cache;

import redis.clients.jedis.Jedis;

/**
 * @Description TODO
 * @Author fang
 * @Date 2019-11-22 12:03
 **/
public interface IRedisStandaloneConsumer<T> {
    T handle(Jedis jedis) throws Exception;
}
