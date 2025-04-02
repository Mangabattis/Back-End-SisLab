package com.ucsal.pimbas.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucsal.pimbas.entities.dtos.UsuarioDTO;
import com.ucsal.pimbas.entities.enums.TipoUsuario;
import com.ucsal.pimbas.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        if(usuarioDTO.getTipoUsuario().equals(TipoUsuario.PROFESSOR)){
            return ResponseEntity.ok(usuarioService.registrarProfessor(usuarioDTO));
        } else if (usuarioDTO.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)) {
            return ResponseEntity.ok(usuarioService.registrarAdministrador(usuarioDTO));
        } else {
            throw new RuntimeException("Tipo de Usuário Inválido");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> VerificarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        boolean existe = usuarioService.UsuarioExiste(usuarioDTO.getEmail(), usuarioDTO.getSenha());

        if(existe){
            return ResponseEntity.ok(Collections.singletonMap("mensagem", "Login bem-sucedido"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("erro", "Email ou senha incorretos"));
        }
    }

    @GetMapping("/dados")
    public ResponseEntity<?> obterDadosUsuario(@RequestParam String email){
        Map<String, Object> usuario = usuarioService.obterDadosUsuario(email);

        if(usuario != null){
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }
}
