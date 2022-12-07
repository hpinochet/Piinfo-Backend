package com.example.piinfo.repository;

import com.example.piinfo.model.Reminder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderRepository extends MongoRepository<Reminder, String> {
}
