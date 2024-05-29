//package it.jdk.buonappetito.repositories;
//
//import it.jdk.buonappetito.entities.Prenotazione;
//import it.jdk.buonappetito.entities.Ristorante;
//import it.jdk.buonappetito.entities.Utente;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Repository
//public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
//
//    List<Prenotazione> findByRistoranteAndDataOraPrenotazioneBetween(Ristorante ristorante, LocalDateTime start, LocalDateTime end);
//
//}
