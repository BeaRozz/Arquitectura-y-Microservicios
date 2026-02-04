package com.modelo.services.modules.earings.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.modelo.services.modules.earings.model.Erings;

public interface EringsRepository extends MongoRepository<Erings, String> {
    
    Optional<Erings> findById(String id);
    Optional<Erings> findByModel(String model);
    
}
