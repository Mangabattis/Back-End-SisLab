package com.ucsal.pimbas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucsal.pimbas.entities.Software;

public interface SoftwareRepository extends JpaRepository<Software, Long>{
    
}
