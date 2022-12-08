package com.example.piinfo.repository;

import com.example.piinfo.model.Product_User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Product_UserRepository extends MongoRepository<Product_User, String> {
}
