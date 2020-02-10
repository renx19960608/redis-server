package com.renx.commom.redis;

import java.util.List;
import java.util.Set;

public interface RedisClient {
    /**
     * 从Redis中获取关键字对应的值.
     * @param db  db的索引
     * @param key Redis中的关键字
     * @return 对应的值;
     */
    public String get(final int db, final String key);

    public String set(final int db, final String key, final String value);

    public String setex(final int db, final String key, final int seconds, final String value);

    public Boolean exists(final int db, final String key);

    public Long del(final int db, final String... keys);

    public Long expire(final int db, final String key, final int seconds);

    public Long hset(final int db, final String key, final String field, final String value);

    public String hget(final int db, final String key, final String field);

    public Long hdel(final int db, final String key, final String... fields);

    public Long rpush(final int db, final String key, final String... strings);

    public Long lpush(final int db, final String key, final String... strings);

    public Long lpush(final int db, final String key, final String value);

    public Long llen(final int db, final String key);

    public List<String> lrange(final int db, final String key, final long start,
                               final long end);

    public String ltrim(final int db, final String key, final long start, final long end);

    public String lindex(final int db, final String key, final long index);

    public String lset(final int db, final String key, final long index, final String value);

    public String lpop(final int db, final String key);

    public String rpop(final int db, final String key);

    public List<String> brpop(final int db, final String key, final int timeout);

    public Boolean hexists(final int db, final String key, final String field);

    public Set<String> hkeys(final int db, final String key);

    public Long ttl(final int db, final String key);

    public Set<String> keys(final int db, final String pattern);

    public List<String> mget(final int db, final String... keys);
}
