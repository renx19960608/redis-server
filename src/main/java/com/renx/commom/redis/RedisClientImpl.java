package com.renx.commom.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

public class RedisClientImpl implements RedisClient {
    private Logger log=LoggerFactory.getLogger(RedisClientImpl.class);

    @Autowired
    private JedisPool jedisPool;

    public interface RedisCallBack<T>{
       T  execute(Jedis jedis);
    }

    /**
     * 获取键值.
     * @param key		键
     * @return			值
     */
    public String get(final int db,final String key) {
        return run(new RedisCallBack<String>() {
            public String execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.get(key);
            }
        });
    }

    /**
     * 设置键值(字符串).
     * @param key		键
     * @param value		值
     * @return			返回码
     */
    public String set(final int db,final String key, final String value) {
        return run(new RedisCallBack<String>() {
            public String execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.set(key, value);
            }
        });
    }

    public String setex(final int db,final String key, final int seconds, final String value){
        return run(new RedisCallBack<String>() {
            public String execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.setex(key,seconds,value);
            }
        });
    }
    public Boolean exists(final int db,final String key){
        return run(new RedisCallBack<Boolean>() {
            public Boolean execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.exists(key);
            }
        });
    }
    public Long del(final int db,final String... keys){
        return run(new RedisCallBack<Long>() {
            public Long execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.del(keys);
            }
        });
    }

    public Long expire(final int db,final String key, final int seconds){
        return run(new RedisCallBack<Long>() {
            public Long execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.expire(key,seconds);
            }
        });
    }

    public Long hset(final int db,final String key, final String field, final String value){
        return run(new RedisCallBack<Long>() {
            public Long execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.hset(key,field,value);
            }
        });
    }

    public String hget(final int db,final String key, final String field){
        return run(new RedisCallBack<String>() {
            public String execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.hget(key,field);
            }
        });
    }

    public Long hdel(final int db,final String key, final String... fields){
        return run(new RedisCallBack<Long>() {
            public Long execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.hdel(key,fields);
            }
        });
    }

    public Long rpush(final int db,final String key, final String... strings){
        return run(new RedisCallBack<Long>() {
            public Long execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.rpush(key,strings);
            }
        });
    }

    public Long lpush(final int db,final String key, final String... strings){
        return run(new RedisCallBack<Long>() {
            public Long execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.lpush(key,strings);
            }
        });
    }

    public Long lpush(final int db, final String key, final String value){
        return run(new RedisCallBack<Long>() {
            public Long execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.lpush(key,value);
            }
        });
    }

    public Long llen(final int db,final String key){
        return run(new RedisCallBack<Long>() {
            public Long execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.llen(key);
            }
        });
    }

    public List<String> lrange(final int db,final String key, final long start,
                               final long end){
        return run(new RedisCallBack<List<String>>() {
            public List<String> execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.lrange(key,start,end);
            }
        });
    }

    public String ltrim(final int db,final String key, final long start, final long end) {
        return run(new RedisCallBack<String>() {
            public String execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.ltrim(key,start,end);
            }
        });
    }

    public String lindex(final int db,final String key, final long index){
        return run(new RedisCallBack<String>() {
            public String execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.lindex(key,index);
            }
        });
    }

    public String lset(final int db,final String key, final long index, final String value) {
        return run(new RedisCallBack<String>() {
            public String execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.lset(key,index,value);
            }
        });
    }

    public String lpop(final int db,final String key){
        return run(new RedisCallBack<String>() {
            public String execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.lpop(key);
            }
        });
    }
    public List<String> brpop(final int db, final String key,final int timeout){
        return run(new RedisCallBack<List<String>>() {
            public List<String> execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.brpop(timeout,key);
            }
        });
    }
    public String rpop(final int db,final String key){
        return run(new RedisCallBack<String>() {
            public String execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.rpop(key);
            }
        });
    }
    public Boolean hexists(final int db,final String key, final String field){
        return run(new RedisCallBack<Boolean>() {
            public Boolean execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.hexists(key,field);
            }
        });
    }

    public Set<String> hkeys(final int db,final String key){
        return run(new RedisCallBack<Set<String>>() {
            public Set<String> execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.hkeys(key);
            }
        });
    }

    public Long ttl(final int db,final String key){
        return run(new RedisCallBack<Long>() {
            public Long execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.ttl(key);
            }
        });
    }

    public Set<String> keys(final int db, final String pattern) {
        return run(new RedisCallBack<Set<String>>() {
            public Set<String> execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.keys(pattern);
            }
        });
    }

    public List<String> mget(final int db, final String... keys){
        return run(new RedisCallBack<List<String>>() {
            public List<String> execute(final Jedis jedis) {
                jedis.select(db);
                return jedis.mget(keys);
            }
        });
    }

    public <T> T run(RedisCallBack<T> callBack){
        T value=null;
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            value=callBack.execute(jedis);
        }catch (Exception e){
            log.error("redis操作异常:"+e.getMessage());
        }finally {
            returnResource(jedisPool,jedis);//关闭连接，释放资源
        }
        return value;
    }

    public void returnResource(final JedisPool jedisPool,final Jedis redis){
        if(redis!=null){
            jedisPool.returnResource(redis);
        }
    }
}
