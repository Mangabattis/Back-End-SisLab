package com.ucsal.pimbas.entities;

import java.time.LocalDate;

import com.ucsal.pimbas.entities.enums.StatusSolicitacao;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SolicitacaoInstalacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "software_id")
    private Software software;

    @ManyToOne
    @JoinColumn(name = "laboratorio_id")
    private Laboratorio laboratorio;

    private LocalDate dataSolicitacao;
    private LocalDate dataUtilizacao;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status;

    public SolicitacaoInstalacao(Professor professor, Software software, Laboratorio laboratorio, LocalDate dataSolicitacao, LocalDate dataUtilizacao, StatusSolicitacao status){
        this.professor = professor;
        this.software = software;
        this.laboratorio = laboratorio;
        this.dataSolicitacao = dataSolicitacao;
        this.dataUtilizacao = dataUtilizacao;
        this.status = status;
    }
}
