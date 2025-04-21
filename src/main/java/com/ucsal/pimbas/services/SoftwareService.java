package com.ucsal.pimbas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ucsal.pimbas.entities.Software;
import com.ucsal.pimbas.repositories.SoftwareRepository;

@Service
public class SoftwareService {
    
    private final SoftwareRepository softwareRepository;

    public SoftwareService(SoftwareRepository softwareRepository){
        this.softwareRepository = softwareRepository;
    }

    public List<Software> listarSoftwares(){
        return softwareRepository.findAll();
    }
    
    public Software criarSoftware(Software software) {
        return softwareRepository.save(software);
    }
    
    public Software atualizarSoftware(Software software) {
        // Verificar se o software existe
        if (!softwareRepository.existsById(software.getId())) {
            throw new RuntimeException("Software não encontrado");
        }
        return softwareRepository.save(software);
    }
    
    public void excluirSoftware(Long id) {
        // Verificar se o software existe
        if (!softwareRepository.existsById(id)) {
            throw new RuntimeException("Software não encontrado");
        }
        softwareRepository.deleteById(id);
    }
    
    public Software alternarDisponibilidade(Long id) {
        Optional<Software> softwareOpt = softwareRepository.findById(id);
        if (softwareOpt.isEmpty()) {
            throw new RuntimeException("Software não encontrado");
        }
        
        Software software = softwareOpt.get();
        software.setAvailable(!software.isAvailable());
        return softwareRepository.save(software);
    }
}