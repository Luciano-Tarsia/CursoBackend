package com.coderhouse.h2.cache;

import com.coderhouse.h2.config.ApplicationProperties;
import com.coderhouse.h2.model.Restaurante;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheClientImp<T> implements CacheClient<T> {

    private final RedisTemplate<String, T> redisTemplate;
    private final ApplicationProperties properties;
    private HashOperations<String, String, String> hashOperations;
    private final ObjectMapper mapper;

    @PostConstruct
    void setHashOperations() {
        hashOperations = this.redisTemplate.opsForHash();
        this.redisTemplate.expire("mensaje-map", Duration.ofMillis(6000));
    }

    @Override
    public T save(String key, T data) {
        try {
            hashOperations.put("mensaje-map", key, serializeItem(data));
            return data;
        } catch (JsonProcessingException e) {
            log.error("Error converting restaurante to string", e);
        }
        return data;
    }

    @Override
    public T recover(String key, Class<T> classValue) {
        try {
            var item = hashOperations.get("mensaje-map", key);
            if (item == null) return null;
            return deserializeItem(item, classValue);
        } catch (JsonProcessingException e) {
            log.error("Error converting restaurante to Restaurante", e);
        }
        return null;
    }

    @Override
    public void update(Restaurante restaurante){
        save(restaurante.getId().toString(), (T) restaurante);
    }

    @Override
    public void delete(String id){
        hashOperations.delete("mensaje-map",id);
    }

    private String serializeItem(T item) throws JsonProcessingException {
        var serializeItem = mapper.writeValueAsString(item);
        log.info("Mensaje en formato String: {}", serializeItem);
        return serializeItem;
    }

    private T deserializeItem(String jsonInput, Class<T> classValue) throws JsonProcessingException {
        return mapper.readValue(jsonInput, classValue);
    }
}
