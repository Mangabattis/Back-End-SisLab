package com.ucsal.pimbas.services.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ucsal.pimbas.entities.Software;
import com.ucsal.pimbas.repositories.SoftwareRepository;
import com.ucsal.pimbas.repositories.interfaces.ISoftware;

@Repository
public class SoftwareImpl implements ISoftware{

    private final SoftwareRepository softwareRepository;

    public SoftwareImpl(SoftwareRepository softwareRepository){
        this.softwareRepository = softwareRepository;
    }

    @Override
    public List<Software> listarSoftwares() {
        return softwareRepository.findAll();
    }
    
}
