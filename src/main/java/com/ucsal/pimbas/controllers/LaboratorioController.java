package com.ucsal.pimbas.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucsal.pimbas.entities.Laboratorio;
import com.ucsal.pimbas.services.LaboratorioService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/laboratorio")
@CrossOrigin("*")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    public LaboratorioController(LaboratorioService laboratorioService){
        this.laboratorioService = laboratorioService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Laboratorio>> listarLaboratorios() {
        return ResponseEntity.ok(laboratorioService.listarLaboratorios());
    }
    
}
