package com.ucsal.pimbas.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ucsal.pimbas.entities.Administrador;
import com.ucsal.pimbas.entities.Professor;
import com.ucsal.pimbas.entities.dtos.UsuarioDTO;
import com.ucsal.pimbas.repositories.interfaces.IUsuario;

@Service
public class UsuarioService {
    
    private final IUsuario usuario;

    public UsuarioService(IUsuario usuario){
        this.usuario = usuario;
    }

    public Professor registrarProfessor(UsuarioDTO userDTO) {
        return usuario.registrarProfessor(userDTO);
    }

    public Administrador registrarAdministrador(UsuarioDTO userDTO) {
        return usuario.registrarAdministrador(userDTO);
    }

    public boolean UsuarioExiste(String email, String senha){
        return usuario.UsuarioExiste(email, senha);
    }

    public Map<String, Object> obterDadosUsuario(String email){
        return usuario.obterDadosUsuario(email);
    }

}
