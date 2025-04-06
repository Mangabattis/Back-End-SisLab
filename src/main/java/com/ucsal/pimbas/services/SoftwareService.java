package com.ucsal.pimbas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ucsal.pimbas.entities.Software;
import com.ucsal.pimbas.repositories.interfaces.ISoftware;

@Service
public class SoftwareService {
    
    private final ISoftware software;

    public SoftwareService(ISoftware software){
        this.software = software;
    }

    public List<Software> listarSoftwares(){
        return software.listarSoftwares();
    }
}
