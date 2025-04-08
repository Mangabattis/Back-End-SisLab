package com.ucsal.pimbas.services.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ucsal.pimbas.entities.Laboratorio;
import com.ucsal.pimbas.entities.Software;
import com.ucsal.pimbas.entities.SolicitacaoInstalacao;
import com.ucsal.pimbas.entities.dtos.SolicitacaoInstalacaoDTO;
import com.ucsal.pimbas.entities.enums.StatusSolicitacao;
import com.ucsal.pimbas.repositories.LaboratorioRepository;
import com.ucsal.pimbas.repositories.SoftwareRepository;
import com.ucsal.pimbas.repositories.SolicitacaoInstalacaoRepository;
import com.ucsal.pimbas.repositories.interfaces.ISolicitacaoSoftware;

@Repository
public class SolicitacaoSoftwareImpl implements ISolicitacaoSoftware{

    private final SolicitacaoInstalacaoRepository solicitacaoRepository;
    private final LaboratorioRepository laboratorioRepository;
    private final SoftwareRepository  softwareRepository;

        public SolicitacaoSoftwareImpl(SolicitacaoInstalacaoRepository solicitacaoRepository, LaboratorioRepository laboratorioRepository, SoftwareRepository  softwareRepository){
            this.solicitacaoRepository = solicitacaoRepository;
            this.laboratorioRepository = laboratorioRepository;
            this.softwareRepository = softwareRepository;
        }

    @Override
    public SolicitacaoInstalacaoDTO criarSolicitacao(SolicitacaoInstalacaoDTO dto) {
        Laboratorio lab = laboratorioRepository.findById(dto.getLaboratorioId()).orElseThrow();
        List<Software> softwares = softwareRepository.findAllById(dto.getSoftwareIds());

        for (Software software : softwares) {
            boolean existe = solicitacaoRepository.existsByLaboratorioAndSoftware(lab.getId(), software.getId());
            if (existe) {
                throw new RuntimeException("O software '" + software.getName() + "' já está solicitado para este laboratório.");
            }
        }

        SolicitacaoInstalacao solicitacao = new SolicitacaoInstalacao(
            dto.getDataUso(),
            StatusSolicitacao.PENDENTE,
            lab,
            softwares
        );

        System.out.println(solicitacao.getDataUso()+"-"+solicitacao.getStatus()+"-"+solicitacao.getLaboratorio()+"-"+solicitacao.getSoftwares());
        solicitacaoRepository.save(solicitacao);
        dto.setId(solicitacao.getId());
        return dto;
    }

    @Override
    public List<SolicitacaoInstalacaoDTO> listarSolicitacoes() {
        return solicitacaoRepository.findAll().stream().map(solicitacao -> {
            SolicitacaoInstalacaoDTO dto = new SolicitacaoInstalacaoDTO();
            dto.setId(solicitacao.getId());
            dto.setDataUso(solicitacao.getDataUso());
            dto.setStatus(solicitacao.getStatus().name());
            dto.setLaboratorioId(solicitacao.getLaboratorio().getId());
            dto.setSoftwareIds(
                solicitacao.getSoftwares().stream().map(Software::getId).toList()
            );
            return dto;
        }).toList();
    }
}
