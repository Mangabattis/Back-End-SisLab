package com.ucsal.pimbas.services.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ucsal.pimbas.entities.Laboratorio;
import com.ucsal.pimbas.repositories.LaboratorioRepository;
import com.ucsal.pimbas.repositories.interfaces.ILaboratorio;

@Repository
public class LaboratorioImpl implements ILaboratorio{

    private final LaboratorioRepository laboratorioRepository;

    public LaboratorioImpl(LaboratorioRepository laboratorioRepository){
        this.laboratorioRepository = laboratorioRepository;
    }

    @Override
    public List<Laboratorio> listarLaboratorios() {
        return laboratorioRepository.findAll();
    }
    
}
