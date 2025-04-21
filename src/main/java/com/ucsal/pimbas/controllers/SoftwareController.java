package com.ucsal.pimbas.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucsal.pimbas.entities.Software;
import com.ucsal.pimbas.services.SoftwareService;

@RestController
@RequestMapping("software")
@CrossOrigin("*")
public class SoftwareController {

    private final SoftwareService softwareService;

    public SoftwareController(SoftwareService softwareService){
        this.softwareService = softwareService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Software>> listarSoftwares(){
        return ResponseEntity.ok(softwareService.listarSoftwares());
    }
    
    @PostMapping("/criar")
    public ResponseEntity<?> criarSoftware(@RequestBody Software software) {
        try {
            Software novoSoftware = softwareService.criarSoftware(software);
            return ResponseEntity.ok(novoSoftware);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro ao criar software: " + e.getMessage()));
        }
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarSoftware(@PathVariable Long id, @RequestBody Software software) {
        try {
            software.setId(id); // Garante que o ID está correto
            Software softwareAtualizado = softwareService.atualizarSoftware(software);
            return ResponseEntity.ok(softwareAtualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro ao atualizar software: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirSoftware(@PathVariable Long id) {
        try {
            softwareService.excluirSoftware(id);
            return ResponseEntity.ok(Map.of("message", "Software excluído com sucesso."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro ao excluir software: " + e.getMessage()));
        }
    }
    
    @PutMapping("/disponibilidade/{id}")
    public ResponseEntity<?> alternarDisponibilidade(@PathVariable Long id) {
        try {
            Software software = softwareService.alternarDisponibilidade(id);
            return ResponseEntity.ok(software);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro ao alterar disponibilidade: " + e.getMessage()));
        }
    }
}