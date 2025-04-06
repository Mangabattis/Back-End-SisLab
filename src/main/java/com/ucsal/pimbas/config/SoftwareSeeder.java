package com.ucsal.pimbas.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ucsal.pimbas.entities.Software;
import com.ucsal.pimbas.repositories.SoftwareRepository;

@Configuration
public class SoftwareSeeder {

    @Bean
    CommandLineRunner initSoftwares(SoftwareRepository softwareRepository){
        return args -> {
            if (softwareRepository.count() == 0) {
                softwareRepository.save(new Software("Visual Studio Code", "https://code.visualstudio.com/", "1.78.2", true, true));
                softwareRepository.save(new Software("Adobe Photoshop", "https://www.adobe.com/products/photoshop.html", "24.0", false, false));
                softwareRepository.save(new Software("MATLAB", "https://www.mathworks.com/products/matlab.html", "R2023a", false, false));
                softwareRepository.save(new Software("Python", "https://www.python.org/", "3.11.3", true, true));
                softwareRepository.save(new Software("Unity", "https://unity.com/", "2022.3", true, true));
                softwareRepository.save(new Software("IntelliJ IDEA", "https://www.jetbrains.com/idea/", "2023.1", false, true));
                softwareRepository.save(new Software("AutoCAD", "https://www.autodesk.com/products/autocad/", "2023", false, false));
                softwareRepository.save(new Software("SPSS", "https://www.ibm.com/products/spss-statistics", "28.0", false, false));
            }
        };
    }

}
