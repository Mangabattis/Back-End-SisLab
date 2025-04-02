package com.ucsal.pimbas.repositories.interfaces;

import java.util.Map;

import com.ucsal.pimbas.entities.Administrador;
import com.ucsal.pimbas.entities.Professor;
import com.ucsal.pimbas.entities.dtos.UsuarioDTO;

public interface IUsuario {
    Professor registrarProfessor(UsuarioDTO user);
    Administrador registrarAdministrador(UsuarioDTO user);
    boolean UsuarioExiste(String email, String senha);
    Map<String, Object> obterDadosUsuario(String email);
}
