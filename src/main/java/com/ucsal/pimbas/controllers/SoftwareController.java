package com.ucsal.pimbas.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    
}
