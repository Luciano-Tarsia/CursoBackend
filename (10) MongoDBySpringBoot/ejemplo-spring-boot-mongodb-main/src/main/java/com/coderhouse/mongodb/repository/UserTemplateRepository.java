package com.coderhouse.mongodb.repository;

import com.coderhouse.mongodb.model.Product;

import java.util.List;

public interface UserTemplateRepository  {

    List<Product> findAllByCategoriaSortedLimit(String categoria, String orderBy, int limit);
}
