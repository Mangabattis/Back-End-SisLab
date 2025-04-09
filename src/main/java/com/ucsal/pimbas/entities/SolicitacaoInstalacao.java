package com.ucsal.pimbas.entities;

import java.time.LocalDate;
import java.util.List;

import com.ucsal.pimbas.entities.enums.StatusSolicitacao;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    private LocalDate dataUso;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status;

    @ManyToOne
    @JoinColumn(name = "laboratorio_id")
    private Laboratorio laboratorio;

    @ManyToMany
    @JoinTable(
        name = "solicitacao_software_software",
        joinColumns = @JoinColumn(name = "solicitacao_id"),
        inverseJoinColumns = @JoinColumn(name = "software_id")
    )
    private List<Software> softwares;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    public SolicitacaoInstalacao(LocalDate dataUso, StatusSolicitacao status, Laboratorio laboratorio,  List<Software> softwares){
        this.dataUso = dataUso;
        this.status = status;
        this.laboratorio = laboratorio;
        this.softwares = softwares;
    }

}
