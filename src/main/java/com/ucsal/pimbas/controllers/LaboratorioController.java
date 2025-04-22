package com.ucsal.pimbas.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ucsal.pimbas.entities.Laboratorio;
import com.ucsal.pimbas.services.LaboratorioService;

@RestController
@RequestMapping("/laboratorio")
@CrossOrigin("*")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    public LaboratorioController(LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Laboratorio>> listarLaboratorios() {
        return ResponseEntity.ok(laboratorioService.listarLaboratorios());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return laboratorioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/criar")
    public ResponseEntity<Laboratorio> salvar(@RequestBody Laboratorio laboratorio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(laboratorioService.salvar(laboratorio));
    }
    
    @PutMapping("/atualizar")
    public ResponseEntity<Laboratorio> atualizar(@RequestBody Laboratorio laboratorio) {
        return ResponseEntity.ok(laboratorioService.atualizar(laboratorio));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        laboratorioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/disponibilidade")
    public ResponseEntity<Void> alterarDisponibilidade(@PathVariable Long id, @RequestParam boolean disponivel) {
        laboratorioService.alterarDisponibilidade(id, disponivel);
        return ResponseEntity.ok().build();
    }
}