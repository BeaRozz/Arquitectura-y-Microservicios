package com.modelo.services.modules.cows.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.modelo.services.modules.cows.model.Cows;

public interface CowsRepository extends MongoRepository<Cows, String> {
    
    Optional<Cows> findById(String id);
}

