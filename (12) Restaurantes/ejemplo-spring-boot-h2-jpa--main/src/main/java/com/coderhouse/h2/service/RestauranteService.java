package com.coderhouse.h2.service;

import com.coderhouse.h2.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class RestauranteService {

    @Autowired
    private MongoTemplate mongoTemplate;
    private final ObjectMapper mapper;

    private static final Logger log = LoggerFactory.getLogger(RestauranteService.class);

    @PostConstruct
    private void PostConstruct() {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
    }

    public Restaurante createRestaurante(Restaurante restaurante) {
        try {
            mapperToString(restaurante);
            mapperToMap(restaurante);
            mapperToClass(restaurante);

            return mongoTemplate.save(restaurante);
        } catch (JsonProcessingException e) {
            log.error("Error converting message to string", e);
        }
        return restaurante;
    }

    void mapperToString(Restaurante restaurante) throws JsonProcessingException {
        var restauranteString = mapper.writeValueAsString(restaurante);
        log.info("Mensaje en formato String : {}", restauranteString);
    }
    void mapperToMap(Restaurante restaurante) throws JsonProcessingException {
        var restauranteString = mapper.writeValueAsString(restaurante);
        var restauranteMap = mapper.readValue(restauranteString, Map.class);
        log.info("Mensaje en formato de Mapa : {}", restauranteMap);
    }

    void mapperToClass(Restaurante restaurante) throws JsonProcessingException {
        var restauranteString = mapper.writeValueAsString(restaurante);
        var restauranteClass = mapper.readValue(restauranteString, Restaurante.class);
        log.info("Mensaje en formato de Clase : {}", restauranteClass);
    }

    public Map<String, Restaurante> restauranteStringToMap(String restaurante) throws  JsonProcessingException{
        Map<String, Restaurante> resMap = mapper.readValue(restaurante, Map.class);
        return resMap;
    }

}
