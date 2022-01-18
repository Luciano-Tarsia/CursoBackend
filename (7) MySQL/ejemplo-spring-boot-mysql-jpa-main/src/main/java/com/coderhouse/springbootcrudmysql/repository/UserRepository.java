package com.coderhouse.springbootcrudmysql.repository;

import com.coderhouse.springbootcrudmysql.model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<Product, Long>  {

    Product findByName(String name);

}
