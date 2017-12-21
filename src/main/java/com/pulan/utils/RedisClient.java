package com.pulan.utils;




import java.util.Set;

import org.springframework.stereotype.Component;

import com.pulan.config.RedisConfig;
import com.pulan.service.IRedisEventHandler;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
@Component
public class RedisClient {
	public void set(int data_base,String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = RedisConfig.jedisPool.getResource();
            jedis.select(data_base);
            jedis.set(key, value);
        } finally {
            //返还到连接池
            if (jedis != null) jedis.close();
        }
    }
    //取出指定Key的所有消息
    public String get(int data_base,String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = RedisConfig.jedisPool.getResource();
            jedis.select(data_base);
            String ret = jedis.get(key);
            if (null == ret) ret = "";
            return ret;
        } finally {
            //返还到连接池
        	if (jedis != null) jedis.close();
        }
    }
	//设置Key过期时间(自动删除)
	public void expire(int data_base,String key, int seconds) throws Exception {
		Jedis jedis = null;
		try {
			jedis = RedisConfig.jedisPool.getResource();
			jedis.select(data_base);
			jedis.expire(key, seconds);
		} finally {
			if (jedis != null) jedis.close();
		}
	}
	//订阅消息
	public void subscrib(String channel, final IRedisEventHandler eventHandler) throws Exception {
		Jedis jedis = null;
		try {
			jedis = RedisConfig.jedisPool.getResource();
			JedisPubSub jedisPubSub = new JedisPubSub() {
				@Override
				public void onMessage(String channel, String message) {
					eventHandler.onEvent(channel, message);
				}
			};
			jedis.subscribe(jedisPubSub, channel);
		} finally {
			if (jedis != null) jedis.close();
		}
	}
	//获取Redis 模糊 Key
		public Set<String> muhuKey(int data_base,String key) throws Exception{
			Set<String> sets =null;
			Jedis jedis =null;
			try {
				jedis =RedisConfig.jedisPool.getResource();
				jedis.select(data_base);
				sets = jedis.keys(key);
				if (sets.isEmpty()){
					sets =null;
				}
				return sets;
			}finally {
				if (jedis!=null)
					jedis.close();
			}
		}
		 //删除消息队列
	    public void del(int data_base,String key) throws Exception {
	        Jedis jedis = null;
	        try {  
	            jedis = RedisConfig.jedisPool.getResource();
	            jedis.select(data_base);
	            jedis.del(key);
	        } finally {
	            //返还到连接池
	        	if (jedis != null) jedis.close();
	        }
	    }
}
