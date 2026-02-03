package com.modelo.services.modules.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.modelo.services.modules.users.model.Usuarios;

import java.util.Optional;


public interface UsuariosRepository extends MongoRepository<Usuarios, String> {
    
    Optional<Usuarios> findByEmail(String email);
}
