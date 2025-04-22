package com.ucsal.pimbas.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter 
@Setter
@NoArgsConstructor
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private Integer capacity;
    private Integer computerCount;
    private String description;
    private boolean available;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "laboratorio_software",
        joinColumns = @JoinColumn(name = "laboratorio_id"),
        inverseJoinColumns = @JoinColumn(name = "software_id")
    )
    private List<Software> installedSoftware;

    public Laboratorio(String name, String location, Integer capacity, Integer computerCount, String description, boolean available) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.computerCount = computerCount;
        this.description = description;
        this.available = available;
    }
}