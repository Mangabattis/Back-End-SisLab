package com.ucsal.pimbas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ucsal.pimbas.entities.dtos.SolicitacaoInstalacaoDTO;
import com.ucsal.pimbas.entities.enums.StatusSolicitacao;
import com.ucsal.pimbas.repositories.interfaces.ISolicitacaoSoftware;

@Service
public class SolicitacaoSoftwareService {

    private final ISolicitacaoSoftware solicitacaoSoftware;

    public SolicitacaoSoftwareService(ISolicitacaoSoftware solicitacaoSoftware){
        this.solicitacaoSoftware = solicitacaoSoftware;
    }

    public SolicitacaoInstalacaoDTO criarSolicitacao(SolicitacaoInstalacaoDTO dto){
        return solicitacaoSoftware.criarSolicitacao(dto);
    }

    public List<SolicitacaoInstalacaoDTO> listarSolicitacoes(){
        return solicitacaoSoftware.listarSolicitacoes();
    }

    public List<SolicitacaoInstalacaoDTO> listarPorProfessor(Long professorId){
        return solicitacaoSoftware.listarPorProfessor(professorId);
    }
    
    public SolicitacaoInstalacaoDTO atualizarStatus(Long solicitacaoId, StatusSolicitacao status) {
        return solicitacaoSoftware.atualizarStatus(solicitacaoId, status);
    }
    
    public SolicitacaoInstalacaoDTO confirmarUso(Long solicitacaoId) {
        return solicitacaoSoftware.confirmarUso(solicitacaoId);
    }
}