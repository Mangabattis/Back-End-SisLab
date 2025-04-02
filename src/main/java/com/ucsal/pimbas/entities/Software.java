package com.ucsal.pimbas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor 
public class Software {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String linkInstalacao;
    private String versao;
    private boolean softwareLivre; // true = Livre, false = Proprietário

    private boolean disponivel; // Se o software pode ser instalado

    public Software(String nome, String linkInstalacao, String versao, boolean softwareLivre, boolean disponivel){
        this.nome = nome;
        this.linkInstalacao = linkInstalacao;
        this.versao = versao;
        this.softwareLivre = softwareLivre;
        this.disponivel = disponivel;
    }
}
