package com.example.evoLog_TP3.repository;

import com.example.evoLog_TP3.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    // Additional query methods if needed
}