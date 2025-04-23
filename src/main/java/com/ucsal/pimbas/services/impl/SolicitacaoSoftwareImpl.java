package com.ucsal.pimbas.services.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ucsal.pimbas.entities.Laboratorio;
import com.ucsal.pimbas.entities.Professor;
import com.ucsal.pimbas.entities.Software;
import com.ucsal.pimbas.entities.SolicitacaoInstalacao;
import com.ucsal.pimbas.entities.dtos.SolicitacaoInstalacaoDTO;
import com.ucsal.pimbas.entities.enums.StatusSolicitacao;
import com.ucsal.pimbas.repositories.LaboratorioRepository;
import com.ucsal.pimbas.repositories.ProfessorRepository;
import com.ucsal.pimbas.repositories.SoftwareRepository;
import com.ucsal.pimbas.repositories.SolicitacaoInstalacaoRepository;
import com.ucsal.pimbas.repositories.interfaces.ISolicitacaoSoftware;

@Repository
public class SolicitacaoSoftwareImpl implements ISolicitacaoSoftware {

    private final SolicitacaoInstalacaoRepository solicitacaoRepository;
    private final LaboratorioRepository laboratorioRepository;
    private final SoftwareRepository softwareRepository;
    private final ProfessorRepository professorRepository;

    public SolicitacaoSoftwareImpl(SolicitacaoInstalacaoRepository solicitacaoRepository, 
                                 LaboratorioRepository laboratorioRepository, 
                                 SoftwareRepository softwareRepository, 
                                 ProfessorRepository professorRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.laboratorioRepository = laboratorioRepository;
        this.softwareRepository = softwareRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public SolicitacaoInstalacaoDTO criarSolicitacao(SolicitacaoInstalacaoDTO dto) {
        Laboratorio lab = laboratorioRepository.findById(dto.getLaboratorioId()).orElseThrow();
        List<Software> softwares = softwareRepository.findAllById(dto.getSoftwareIds());
        Professor professor = professorRepository.findById(dto.getProfessorId()).orElseThrow();

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
            softwares,
            dto.getObservacao()
        );

        solicitacao.setProfessor(professor);
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
            dto.setObservacao(solicitacao.getObservacao());

            dto.setLaboratorioId(solicitacao.getLaboratorio().getId());
            dto.setLaboratorioNome(solicitacao.getLaboratorio().getName());

            dto.setSoftwareIds(solicitacao.getSoftwares().stream().map(Software::getId).toList());
            dto.setSoftwareNomes(solicitacao.getSoftwares().stream().map(Software::getName).toList());

            dto.setSolicitadoPor(solicitacao.getProfessor().getNome());
            dto.setProfessorId(solicitacao.getProfessor().getId());

            return dto;
        }).toList();
    }

    @Override
    public List<SolicitacaoInstalacaoDTO> listarPorProfessor(Long professorId) {
        return solicitacaoRepository.findByProfessorId(professorId).stream().map(solicitacao -> {
            SolicitacaoInstalacaoDTO dto = new SolicitacaoInstalacaoDTO();
            dto.setId(solicitacao.getId());
            dto.setDataUso(solicitacao.getDataUso());
            dto.setStatus(solicitacao.getStatus().name());
            dto.setObservacao(solicitacao.getObservacao());

            dto.setLaboratorioId(solicitacao.getLaboratorio().getId());
            dto.setLaboratorioNome(solicitacao.getLaboratorio().getName());

            dto.setSoftwareIds(solicitacao.getSoftwares().stream().map(Software::getId).toList());
            dto.setSoftwareNomes(solicitacao.getSoftwares().stream().map(Software::getName).toList());

            dto.setSolicitadoPor(solicitacao.getProfessor().getNome());
            dto.setProfessorId(solicitacao.getProfessor().getId());

            return dto;
        }).toList();
    }

    @Override
    public SolicitacaoInstalacaoDTO atualizarStatus(Long solicitacaoId, StatusSolicitacao status) {
        SolicitacaoInstalacao solicitacao = solicitacaoRepository.findById(solicitacaoId)
            .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));
        
        solicitacao.setStatus(status);
        solicitacaoRepository.save(solicitacao);
        
        SolicitacaoInstalacaoDTO dto = new SolicitacaoInstalacaoDTO();
        dto.setId(solicitacao.getId());
        dto.setDataUso(solicitacao.getDataUso());
        dto.setStatus(solicitacao.getStatus().name());
        dto.setObservacao(solicitacao.getObservacao());
        dto.setLaboratorioId(solicitacao.getLaboratorio().getId());
        dto.setLaboratorioNome(solicitacao.getLaboratorio().getName());
        dto.setSoftwareIds(solicitacao.getSoftwares().stream().map(Software::getId).toList());
        dto.setSoftwareNomes(solicitacao.getSoftwares().stream().map(Software::getName).toList());
        dto.setSolicitadoPor(solicitacao.getProfessor().getNome());
        dto.setProfessorId(solicitacao.getProfessor().getId());
        
        return dto;
    }

    @Override
    public SolicitacaoInstalacaoDTO confirmarUso(Long solicitacaoId) {
        SolicitacaoInstalacao solicitacao = solicitacaoRepository.findById(solicitacaoId)
            .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));
        
        solicitacao.setStatus(StatusSolicitacao.CONCLUIDO);
        solicitacao.setObservacao("Uso confirmado pelo professor.");
        solicitacaoRepository.save(solicitacao);
        
        SolicitacaoInstalacaoDTO dto = new SolicitacaoInstalacaoDTO();
        dto.setId(solicitacao.getId());
        dto.setDataUso(solicitacao.getDataUso());
        dto.setStatus(solicitacao.getStatus().name());
        dto.setObservacao(solicitacao.getObservacao());
        dto.setLaboratorioId(solicitacao.getLaboratorio().getId());
        dto.setLaboratorioNome(solicitacao.getLaboratorio().getName());
        dto.setSoftwareIds(solicitacao.getSoftwares().stream().map(Software::getId).toList());
        dto.setSoftwareNomes(solicitacao.getSoftwares().stream().map(Software::getName).toList());
        dto.setSolicitadoPor(solicitacao.getProfessor().getNome());
        dto.setProfessorId(solicitacao.getProfessor().getId());
        
        return dto;
    }
}