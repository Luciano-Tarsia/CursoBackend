package com.coderhouse.h2.repository;

import com.coderhouse.h2.model.Restaurante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurante, Long> {

}
