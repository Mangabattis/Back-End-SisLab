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
    private Long laboratorioId;
    private List<Long> softwareIds;
    private Long professorId;
}
