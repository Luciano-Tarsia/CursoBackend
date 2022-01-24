package com.coderhouse.h2.cache;

import com.coderhouse.h2.model.Restaurante;

import java.util.List;

public interface CacheClient<T> {
    T save(String key, T data);
    T recover(String key, Class<T> classValue);
    void delete(String id);
    void update(Restaurante restaurante);
}
