package com.ucsal.pimbas.entities.dtos;

import com.ucsal.pimbas.entities.enums.TipoUsuario;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String nome;
    private String email;
    private String escola;
    private String senha;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    public UsuarioDTO(String email, String senha){
        this.email = email;
        this.senha = senha;
    }
}
