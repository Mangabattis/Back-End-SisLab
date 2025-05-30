package com.ucsal.pimbas.entities.dtos;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitacaoInstalacaoDTO {
    private Long id;
    private LocalDate dataUso;
    private String status;
    private String observacao;
    
    private Long laboratorioId;
    private String laboratorioNome;
    
    private List<Long> softwareIds;
    private List<String> softwareNomes;
    
    private String solicitadoPor;
    private Long professorId;
}