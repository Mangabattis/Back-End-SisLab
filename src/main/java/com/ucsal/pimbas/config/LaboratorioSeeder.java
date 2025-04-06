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
                laboratorioRepository.save(new Laboratorio("Laboratório 101", true, 30));
                laboratorioRepository.save(new Laboratorio("Laboratório 102", true, 25));
                laboratorioRepository.save(new Laboratorio("Laboratório 103", true, 20));
                laboratorioRepository.save(new Laboratorio("Laboratório 205", true, 40));
                laboratorioRepository.save(new Laboratorio("Laboratório 301", false, 30));
            }
        };
    }
}
