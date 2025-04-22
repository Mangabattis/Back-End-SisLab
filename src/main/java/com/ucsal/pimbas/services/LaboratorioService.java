package com.ucsal.pimbas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ucsal.pimbas.entities.Laboratorio;
import com.ucsal.pimbas.repositories.interfaces.ILaboratorio;

@Service
public class LaboratorioService {
    
    private final ILaboratorio laboratorio;

    public LaboratorioService(ILaboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public List<Laboratorio> listarLaboratorios() {
        return laboratorio.listarLaboratorios();
    }

    public Optional<Laboratorio> buscarPorId(Long id) {
        return laboratorio.buscarPorId(id);
    }

    public Laboratorio salvar(Laboratorio laboratorio) {
        return this.laboratorio.salvar(laboratorio);
    }

    public void excluir(Long id) {
        laboratorio.excluir(id);
    }

    public Laboratorio atualizar(Laboratorio laboratorio) {
        return this.laboratorio.atualizar(laboratorio);
    }

    public void alterarDisponibilidade(Long id, boolean disponibilidade) {
        laboratorio.alterarDisponibilidade(id, disponibilidade);
    }
}