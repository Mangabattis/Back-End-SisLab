package com.ucsal.pimbas.repositories.interfaces;

import java.util.List;

import com.ucsal.pimbas.entities.dtos.SolicitacaoInstalacaoDTO;
import com.ucsal.pimbas.entities.enums.StatusSolicitacao;

public interface ISolicitacaoSoftware {
    SolicitacaoInstalacaoDTO criarSolicitacao(SolicitacaoInstalacaoDTO dto);
    List<SolicitacaoInstalacaoDTO> listarSolicitacoes();
    List<SolicitacaoInstalacaoDTO> listarPorProfessor(Long professorId);
    SolicitacaoInstalacaoDTO atualizarStatus(Long solicitacaoId, StatusSolicitacao status);
    SolicitacaoInstalacaoDTO confirmarUso(Long solicitacaoId);
}