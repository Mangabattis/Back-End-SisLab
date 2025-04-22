package com.ucsal.pimbas.repositories.interfaces;

import java.util.List;
import java.util.Optional;

import com.ucsal.pimbas.entities.Laboratorio;

public interface ILaboratorio {
    List<Laboratorio> listarLaboratorios();
    Optional<Laboratorio> buscarPorId(Long id);
    Laboratorio salvar(Laboratorio laboratorio);
    void excluir(Long id);
    Laboratorio atualizar(Laboratorio laboratorio);
    void alterarDisponibilidade(Long id, boolean disponibilidade);
}