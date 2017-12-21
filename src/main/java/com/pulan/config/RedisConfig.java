package com.pulan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
@Configuration
public class RedisConfig {
	@Value("${jedis.pool.maxTotal}")
	private int maxTotal;
	
    @Value("${jedis.pool.maxIdle}")
    private int maxIdle;
    
    @Value("${jedis.pool.maxWaitMillis}")
    private int maxWaitMillis;
    
	@Value("${jedis.pool.host}")
	private String host;
	
	@Value("${jedis.pool.port}")
	private int port;
	
    @Value("${jedis.pool.passwd}")
    private String passwd;
    
    public static JedisPool jedisPool;
    @Bean(name= "jedisPool")
    @Autowired  
    public JedisPool jedisPool(@Qualifier("jedisPoolConfig") JedisPoolConfig config) {
		JedisPool pool = new JedisPool(config, host, port, 3000, passwd);
		RedisConfig.jedisPool = pool;
        return pool;
    }

    @Bean(name= "jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig () {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        return config;
    }
}
