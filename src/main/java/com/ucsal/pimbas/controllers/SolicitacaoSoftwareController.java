package com.ucsal.pimbas.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucsal.pimbas.entities.dtos.SolicitacaoInstalacaoDTO;
import com.ucsal.pimbas.entities.enums.StatusSolicitacao;
import com.ucsal.pimbas.services.SolicitacaoSoftwareService;

@RestController
@RequestMapping("/solicitacao")
@CrossOrigin("*")
public class SolicitacaoSoftwareController {
    
    private final SolicitacaoSoftwareService solicitacaoService;

    public SolicitacaoSoftwareController(SolicitacaoSoftwareService solicitacaoService){
        this.solicitacaoService = solicitacaoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarSolicitacao(@RequestBody SolicitacaoInstalacaoDTO dto) {
        try {
            solicitacaoService.criarSolicitacao(dto);
            return ResponseEntity.ok("Solicitação criada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro ao salvar solicitação: " + e.getMessage()));
        }
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<SolicitacaoInstalacaoDTO>> listarSolicitacoes(){
        return ResponseEntity.ok(solicitacaoService.listarSolicitacoes());
    }

    @GetMapping("/listar/professor/{idProfessor}")
    public ResponseEntity<List<SolicitacaoInstalacaoDTO>> listarPorProfessor(@PathVariable Long idProfessor){
        return ResponseEntity.ok(solicitacaoService.listarPorProfessor(idProfessor));
    }
    
    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<?> atualizarStatus(@PathVariable Long id, @PathVariable String status) {
        try {
            StatusSolicitacao statusEnum = StatusSolicitacao.valueOf(status.toUpperCase());
            SolicitacaoInstalacaoDTO solicitacao = solicitacaoService.atualizarStatus(id, statusEnum);
            return ResponseEntity.ok(solicitacao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro ao atualizar status: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{id}/confirmar-uso")
    public ResponseEntity<?> confirmarUso(@PathVariable Long id) {
        try {
            SolicitacaoInstalacaoDTO solicitacao = solicitacaoService.confirmarUso(id);
            return ResponseEntity.ok(solicitacao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro ao confirmar uso: " + e.getMessage()));
        }
    }
}