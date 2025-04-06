package com.ucsal.pimbas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor 
public class Software {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String linkInstall;
    private String version;
    private boolean softwareFree; // true = Livre, false = Proprietário

    private boolean available; // Se o software pode ser instalado

    public Software(String name, String linkInstall, String version, boolean softwareFree, boolean available){
        this.name = name;
        this.linkInstall = linkInstall;
        this.version = version;
        this.softwareFree = softwareFree;
        this.available = available;
    }
}
