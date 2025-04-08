package com.ucsal.pimbas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ucsal.pimbas.entities.SolicitacaoInstalacao;

public interface SolicitacaoInstalacaoRepository extends JpaRepository<SolicitacaoInstalacao, Long>{
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM SolicitacaoInstalacao s JOIN s.softwares sw WHERE s.laboratorio.id = :labId AND sw.id = :softwareId")
    boolean existsByLaboratorioAndSoftware(@Param("labId") Long labId, @Param("softwareId") Long softwareId);

}
