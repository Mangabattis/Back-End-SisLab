package com.ucsal.pimbas.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ucsal.pimbas.entities.Laboratorio;
import com.ucsal.pimbas.repositories.LaboratorioRepository;

@Configuration
public class LaboratorioSeeder {

    @Bean
    CommandLineRunner initLaboratorios(LaboratorioRepository laboratorioRepository) {
        return args -> {
            if (laboratorioRepository.count() == 0) {
                laboratorioRepository.save(new Laboratorio("Laboratório 101", "Bloco A", 30, 30, "Laboratório principal", true));
                laboratorioRepository.save(new Laboratorio("Laboratório 102", "Bloco B", 25, 25, "Laboratório secundário", true));
                laboratorioRepository.save(new Laboratorio("Laboratório 103", "Bloco C", 20, 20, "Laboratório de redes", true));
                laboratorioRepository.save(new Laboratorio("Laboratório 205", "Bloco D", 40, 40, "Laboratório de sistemas", true));
                laboratorioRepository.save(new Laboratorio("Laboratório 301", "Bloco E", 30, 30, "Laboratório desativado", false));
            }
        };
    }
}
