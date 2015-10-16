package com.ty.photography.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class RedisUtil {
	private static Logger log = LoggerFactory.getLogger(RedisUtil.class);
	
	private static final String REDIS_CONFIG_FILENAME = "redis.properties";
	private static Jedis jedis;
	private static JedisPool jedisPool;
	
	static{
		InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(REDIS_CONFIG_FILENAME);
            Properties props = new Properties();
            props.load(is);
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.parseInt(props.getProperty("redis_maxTotal")));
            config.setMaxIdle(Integer.parseInt(props.getProperty("redis_setMaxIdle")));
            config.setMinIdle(Integer.parseInt(props.getProperty("redis_setMinIdle")));
            config.setMaxWaitMillis(Long.valueOf(props.getProperty("redis_maxWait")));
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
            config.setTestWhileIdle(true);
            jedisPool = new JedisPool(config,
                  props.getProperty("redis_host"),
                  Integer.parseInt(props.getProperty("redis_port")),
                  Integer.parseInt(props.getProperty("redis_timeout")));
        } catch (Exception e) {
            log.error("init error", e);
        } finally {
            if (null != is ) {
                try {
                    is.close();
                } catch (IOException e) {
//                    e.printStackTrace();
                    log.error("process failed",e);
                }
            }
        }
	}
	
	public RedisUtil(){
		jedis =  jedisPool.getResource();
	}
	
	public void close(){
		jedisPool.returnResourceObject(jedis);
	}
	/**
     * 加1操作
     * @param key
     * @return
     */
    public Long incr(String key) {
        Long ret = 0l;
        try {
            ret = jedis.incr(key);
        } catch (Exception e) {
            log.error("incr", e);
        }
        return ret;
    }
    
	/**
     * 获取并清零
     * @param key
     * @return
     */
    public String getSetZero(String key) {
        String ret = "0";
        try {
            ret = jedis.getSet(key, "0");
        } catch (Exception e) {
            log.error("get", e);
        }
        return ret;
    }
	
	/**
     * 返回 key 所关联的字符串值
     * @param key
     * @return
     */
    public String get(String key) {
        String ret = "";
        try {
            ret = jedis.get(key);
        } catch (Exception e) {
            log.error("get", e);
        }
        return ret;
    }
    /**
     * 返回哈希表 key 中给定域 field 的值。
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
    	String retval = "";
    	try{
            retval = jedis.hget(key, field);
    	}catch(Exception e){
    		log.error("hget",e);
    	}
        return retval;
    }
    
    public Map<String,String> hgetAll(String key) {
    	Map<String,String> retval = new HashMap<String,String>();
    	try{
            retval = jedis.hgetAll(key);
    	}catch(Exception e){
    		log.error("hget",e);
    	}
        return retval;
    }
    
    /**
     *  将值 value 关联到 key 
     * @param key        key
     * @param value      value
     */
    public void set(String key, String value) {
        try {
            jedis.set(key, value);
        } catch (Exception e) {
            log.error("set", e);
        }
    }

    public boolean hset(String key, String field, String value) {
        boolean ret = false;
        try {
            long val = jedis.hset(key, field, value);
            if ((1 == val) || (0 == val)) {
                ret = true;
            }
        } catch (Exception e) {
            log.error("hset", e);
            ret = false;
        } 
        return ret;
    }
	/**
	 * 累加操作
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public Long hincrby(String key,String field,long value){
		Long ret = 0l;
        try {
            ret = jedis.hincrBy(key, field, value);
        } catch (Exception e) {
            log.error("hincrby", e);
        }
        return ret;
	}

    public void setex(String key, int seconds, String value) {
        try {
            jedis.setex(key, seconds, value);
        } catch (Exception e) {
            log.error("setex", e);
        }
    }
    
	/**
	 * 返回集合数
	 * @param key
	 * @return
	 */
	public Long scard(String key){
		Long ret = 0l;
        try {
            ret = jedis.scard(key);
        } catch (Exception e) {
            log.error("get", e);
        }
        return ret;
	}
	/**
	 * 添加集合
	 * @param key
	 * @param value
	 * @return
	 */
	public Long sadd(String key,String... value){
		Long ret = 0l;
        try {
            ret = jedis.sadd(key, value);
        } catch (Exception e) {
            log.error("sadd", e);
        }
        return ret;
	}
	/**
	 * 是否存在
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean sismember(String key,String value){
		boolean ret = false;
        try {
            ret = jedis.sismember(key, value);
        } catch (Exception e) {
            log.error("sismember", e);
        }
        return ret;
	}
	
	public Set<String> sinter(String key){
		Set<String> set = new HashSet<String>();
		try {
			set = jedis.sinter(key);
        } catch (Exception e) {
            log.error("sismember", e);
        }
		return set;
	}
	
    public boolean lpush(String key,String... values){
    	boolean ret = false;
        try {
            long val = jedis.lpush(key, values);
            if ((1 == val) || (0 == val)) {
                ret = true;
            }
        } catch (Exception e) {
            log.error("lpush", e);
            ret = false;
        }
        return ret;
    }
}
