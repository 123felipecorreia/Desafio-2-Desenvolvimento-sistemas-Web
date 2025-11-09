package com.agenciaviagem.repository;

import com.agenciaviagem.model.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinoRepository extends JpaRepository<Destino, Long> {

    /**
     * Pesquisa destinos por nome (case-insensitive)
     */
    List<Destino> findByNomeContainingIgnoreCase(String nome);

    /**
     * Pesquisa destinos por localização (case-insensitive)
     */
    List<Destino> findByLocalizacaoContainingIgnoreCase(String localizacao);

    /**
     * Pesquisa destinos por nome OU localização (case-insensitive)
     */
    @Query("SELECT d FROM Destino d WHERE LOWER(d.nome) LIKE LOWER(CONCAT('%', :termo, '%')) " +
           "OR LOWER(d.localizacao) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Destino> pesquisarPorNomeOuLocalizacao(@Param("termo") String termo);
}
