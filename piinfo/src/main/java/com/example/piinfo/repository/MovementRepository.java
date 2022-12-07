package com.example.piinfo.repository;

import com.example.piinfo.model.Movement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends MongoRepository<Movement,String> {
}
