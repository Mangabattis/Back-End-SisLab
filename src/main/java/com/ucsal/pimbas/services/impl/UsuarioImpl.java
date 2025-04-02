package com.ucsal.pimbas.services.impl;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ucsal.pimbas.entities.Administrador;
import com.ucsal.pimbas.entities.Professor;
import com.ucsal.pimbas.entities.dtos.UsuarioDTO;
import com.ucsal.pimbas.entities.enums.TipoUsuario;
import com.ucsal.pimbas.repositories.AdministradorRepository;
import com.ucsal.pimbas.repositories.ProfessorRepository;
import com.ucsal.pimbas.repositories.interfaces.IUsuario;

@Repository
public class UsuarioImpl implements IUsuario {

    private final ProfessorRepository professorRepository;
    private final AdministradorRepository administradorRepository;

    private final BCryptPasswordEncoder criptografarSenha = new BCryptPasswordEncoder();

    UsuarioImpl(ProfessorRepository professorRepository, AdministradorRepository administradorRepository){
        this.professorRepository = professorRepository;
        this.administradorRepository = administradorRepository;
    }

    @Override
    public Professor registrarProfessor(UsuarioDTO userDTO) {
        if(professorRepository.existsByEmail(userDTO.getEmail())){
            throw new RuntimeException("Email já cadastrado no sistema");
        }

        Professor professor = new Professor(userDTO.getNome(), userDTO.getEmail(), userDTO.getEscola(), criptografarSenha.encode(userDTO.getSenha()));
        System.out.println(professor.getEmail()+"teste"+ professor.getSenha());

        return professorRepository.save(professor);
    }

    @Override
    public Administrador registrarAdministrador(UsuarioDTO userDTO) {
        if(administradorRepository.existsByEmail(userDTO.getEmail())){
            throw new RuntimeException("Email já cadastrado no sistema");
        }

        Administrador administrador = new Administrador(userDTO.getNome(), userDTO.getEmail(), userDTO.getEscola(), criptografarSenha.encode(userDTO.getSenha()));
        System.out.println(administrador.getNome()+"-"+ administrador.getEmail()+"-"+ administrador.getEscola()+"-"+ administrador.getSenha());

        return administradorRepository.save(administrador);
    }

    @Override
    public boolean UsuarioExiste(String email, String senha) {
        Optional<Professor> professorExiste = professorRepository.findByEmail(email);
        Optional<Administrador> adminExiste = administradorRepository.findByEmail(email);
        System.out.println(administradorRepository.findByEmail(email));

        if(professorExiste.isPresent() && criptografarSenha.matches(senha, professorExiste.get().getSenha())){
            System.out.println("Senha normal: "+senha+ " Senha cripto: "+professorExiste.get().getSenha());
            return true;
        } else if(adminExiste.isPresent() && criptografarSenha.matches(senha, adminExiste.get().getSenha())){
            System.out.println("Senha normal: "+senha+ " Senha cripto: "+adminExiste.get().getSenha());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Map<String, Object> obterDadosUsuario(String email) {
        Optional<Professor> professorExiste = professorRepository.findByEmail(email);
        Optional<Administrador> adminExiste = administradorRepository.findByEmail(email);

        if (professorExiste.isPresent()) {
            return Map.of(
                "id", professorExiste.get().getId(),
                "nome", professorExiste.get().getNome(),
                "email", professorExiste.get().getEmail(),
                "escola", professorExiste.get().getEscola(),
                "tipoUsuario", "PROFESSOR"
            );
        } else if (adminExiste.isPresent()) {
            return Map.of(
                "id", adminExiste.get().getId(),
                "nome", adminExiste.get().getNome(),
                "email", adminExiste.get().getEmail(),
                "escola", adminExiste.get().getEscola(),
                "tipoUsuario", "ADMINISTRADOR"
            );
        }

        return null;
    }
}
