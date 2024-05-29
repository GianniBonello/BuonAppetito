package it.jdk.buonappetito.repositories;

import it.jdk.buonappetito.entities.Ristorante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RistoranteRepository extends JpaRepository<Ristorante, Long> {

    @Query("SELECT r FROM Ristorante r WHERE r.comune.nome = :nomeComune")
    List<Ristorante> findByComuneNome(@Param("nomeComune") String nomeComune);
}
