package com.ucsal.pimbas.repositories.interfaces;

import java.util.List;

import com.ucsal.pimbas.entities.dtos.SolicitacaoInstalacaoDTO;

public interface ISolicitacaoSoftware {
    SolicitacaoInstalacaoDTO criarSolicitacao(SolicitacaoInstalacaoDTO dto);
    List<SolicitacaoInstalacaoDTO> listarSolicitacoes();
}
