package com.coderhouse.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.List;

public class RedisRepository {

    public static final String USUARIO_KEY = "USUARIOS";

    private static final Logger logger = LoggerFactory.getLogger(RedisRepository.class);


    private HashOperations<String, String, String> hashOperations;
    @Autowired
    private RedisTemplate redisTemplate;

    public RedisRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(String usuario, String id) {
        hashOperations.put(USUARIO_KEY, id, usuario);
    }

    public String findById(String id) {
        try {
            return  hashOperations.get(USUARIO_KEY, id);
        }catch(JedisConnectionException jedisConnectionException){
            logger.error("error");
            return null;
        }
    }

    public void delete(String id) {
        hashOperations.delete(USUARIO_KEY, id);
    }
    
}
