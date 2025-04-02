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
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String escola;
    private String email;
    private String senha;

    public Administrador(String nome , String email, String escola, String senha){
        this.nome = nome;
        this.escola = escola;
        this.email = email;
        this.senha = senha;

    }

    public Administrador(String email, String senha){
        this.email = email;
        this.senha = senha;
    }
}