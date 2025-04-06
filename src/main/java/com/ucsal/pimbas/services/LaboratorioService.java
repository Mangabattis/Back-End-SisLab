package com.ucsal.pimbas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ucsal.pimbas.entities.Laboratorio;
import com.ucsal.pimbas.repositories.interfaces.ILaboratorio;

@Service
public class LaboratorioService {
    
    private final ILaboratorio laboratorio;

    public LaboratorioService(ILaboratorio laboratorio){
        this.laboratorio = laboratorio;
    }

    public List<Laboratorio> listarLaboratorios(){
        return laboratorio.listarLaboratorios();
    }
}
