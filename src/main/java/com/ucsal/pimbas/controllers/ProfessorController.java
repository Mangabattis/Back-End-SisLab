package com.ucsal.pimbas.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ucsal.pimbas.entities.Professor;
import com.ucsal.pimbas.repositories.ProfessorRepository;
import com.ucsal.pimbas.services.UsuarioService;
import com.ucsal.pimbas.entities.dtos.UsuarioDTO;
import com.ucsal.pimbas.entities.enums.TipoUsuario;

@RestController
@RequestMapping("/api/professores")
@CrossOrigin("*")
public class ProfessorController {

    private final ProfessorRepository professorRepository;
    private final UsuarioService usuarioService;

    public ProfessorController(ProfessorRepository professorRepository, UsuarioService usuarioService) {
        this.professorRepository = professorRepository;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Professor>> listarTodos() {
        return ResponseEntity.ok(professorRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Professor> adicionar(@RequestBody Professor professor) {
        // Criar um DTO para registrar o professor como usuário
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome(professor.getNome());
        usuarioDTO.setEmail(professor.getEmail());
        usuarioDTO.setEscola(professor.getEscola());
        usuarioDTO.setSenha(professor.getSenha());
        usuarioDTO.setTipoUsuario(TipoUsuario.PROFESSOR);
        
        // Registrar o professor usando o serviço de usuário
        Professor novoProfessor = usuarioService.registrarProfessor(usuarioDTO);
        return ResponseEntity.ok(novoProfessor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        professorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable Long id, @RequestBody Professor professor) {
        if (!professorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        professor.setId(id);
        Professor professorAtualizado = professorRepository.save(professor);
        return ResponseEntity.ok(professorAtualizado);
    }
}