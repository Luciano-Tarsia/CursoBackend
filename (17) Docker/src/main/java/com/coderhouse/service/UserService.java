package com.coderhouse.service;

import com.coderhouse.handle.ExceptionUsuario;
import com.coderhouse.model.Factory;
import com.coderhouse.model.Usuario;
import com.coderhouse.repository.MongoRepository;
import com.coderhouse.repository.RedisRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;

import java.text.SimpleDateFormat;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ObjectMapper mapper;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private MongoRepository mongoRepository;

    @Autowired
    private RedisRepository redisRepository;

    private final Factory userFactory = new Factory();

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @PostConstruct
    private void PostConstruct(){
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
    }

    private String mappearAString(Usuario usuario) throws JsonProcessingException {
        String usuarioString = mapper.writeValueAsString(usuario);
        logger.info("Usuario mapeado: \n" +  usuarioString);
        return usuarioString;
    }

    public Usuario crearUsuario(Usuario usuario){
        logger.info("Creación");
        Usuario usuarioDeFactory =  userFactory.crearUsuario(usuario.getNombre(), usuario.getTipo());
        if(usuarioDeFactory == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario ingresado no es valido");
        }
        try{
            String usuarioString = mappearAString(usuarioDeFactory);
            Usuario usuarioNuevo = mongoRepository.save(usuarioDeFactory, "usuarios");
            redisRepository.save(usuarioString, usuarioDeFactory.getId());
            return usuarioNuevo;
        }catch (JsonProcessingException e){
            logger.error("Error en la conversion a string, e");
        }
        return usuario;
    }

    public String getUsuarioById(String id) throws ExceptionUsuario{
        if(mongoRepository.findById(id) != null){
            Usuario usuario = mongoRepository.findById(id);
            String usuarioString = redisRepository.findById(id);
            return usuarioString;
        }else {
            throw new ExceptionUsuario("El usuario que se intentó actualizar no existe");
        }
    }

    private Usuario actualizar(Usuario usuario){
        usuario.setActualizacion("Chau");
        Usuario usuarioActualizado = mongoRepository.save(usuario, "usuarios");
        return usuarioActualizado;
    }

    public  Usuario updateUsuario(Usuario usuario) throws ExceptionUsuario {
        logger.info("Actualización");
        Usuario usuarioDeFactory = userFactory.crearUsuario(usuario.getNombre(), usuario.getTipo());
        if(mongoRepository.findById(usuario.getId()) != null){
            return actualizar(mongoRepository.findById(usuario.getId()));
        }else {
            throw new ExceptionUsuario("El usuario que se intentó actualizar no existe");
        }
    }

    public void deleteByName(String nombre) throws ExceptionUsuario {
        logger.info("Eliminación");
        try {
            Usuario usuario = mongoRepository.findOne((new Query(where("nombre").is(nombre))));
            mongoRepository.findAndRemove((new Query(where("nombre").is(usuario.getNombre()))));
        }catch(NullPointerException n){
            throw new ExceptionUsuario("No se ha encontrado un usuario con ese nombre");
        }
    }

}
