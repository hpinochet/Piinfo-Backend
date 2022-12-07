package com.example.piinfo.repository;

import com.example.piinfo.model.Alert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends MongoRepository<Alert,String> {
}
