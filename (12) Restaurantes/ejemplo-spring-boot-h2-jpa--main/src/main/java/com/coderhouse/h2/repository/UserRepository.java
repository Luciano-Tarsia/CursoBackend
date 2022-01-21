package com.coderhouse.h2.repository;

import com.coderhouse.h2.model.Restaurante;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<Restaurante, Long>  {

    Restaurante findByName(String name);
    List<Restaurante> findByAgeGreaterThan(int age);

}
