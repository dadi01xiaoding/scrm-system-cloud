package com.dadi01.scrm.foundation.cache;

/**
 * @Description TODO
 * @Author fang
 * @Date 2019-11-10 18:02
 **/
public interface IRedisResourceProvider {
    <K> K consume(IRedisClusterConsumer<K> consumer);
    <K> K consume(IRedisStandaloneConsumer<K> consumer);
}
