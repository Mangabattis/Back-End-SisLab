package com.ucsal.pimbas.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ucsal.pimbas.entities.Laboratorio;
import com.ucsal.pimbas.repositories.LaboratorioRepository;
import com.ucsal.pimbas.repositories.interfaces.ILaboratorio;

@Repository
public class LaboratorioImpl implements ILaboratorio {

    private final LaboratorioRepository laboratorioRepository;

    public LaboratorioImpl(LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }

    @Override
    public List<Laboratorio> listarLaboratorios() {
        return laboratorioRepository.findAll();
    }

    @Override
    public Optional<Laboratorio> buscarPorId(Long id) {
        return laboratorioRepository.findById(id);
    }

    @Override
    public Laboratorio salvar(Laboratorio laboratorio) {
        return laboratorioRepository.save(laboratorio);
    }

    @Override
    public void excluir(Long id) {
        laboratorioRepository.deleteById(id);
    }

    @Override
    public Laboratorio atualizar(Laboratorio laboratorio) {
        return laboratorioRepository.save(laboratorio);
    }

    @Override
    public void alterarDisponibilidade(Long id, boolean disponibilidade) {
        laboratorioRepository.findById(id).ifPresent(lab -> {
            lab.setAvailable(disponibilidade);
            laboratorioRepository.save(lab);
        });
    }
}