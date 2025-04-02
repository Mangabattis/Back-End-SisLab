package com.ucsal.pimbas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucsal.pimbas.entities.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>{
    boolean existsByEmail(String email);
    Optional<Administrador> findByEmailAndSenha(String email, String senha);
    Optional<Administrador> findByEmail(String email);
}
