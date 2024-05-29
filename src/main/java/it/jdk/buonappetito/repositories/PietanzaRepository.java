package it.jdk.buonappetito.repositories;

import it.jdk.buonappetito.entities.Pietanza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PietanzaRepository extends JpaRepository<Pietanza, Long> {

}
