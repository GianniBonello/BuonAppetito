//package it.jdk.buonappetito.services;
//
//import it.jdk.buonappetito.DTOs.PrenotazioneDTO;
//import it.jdk.buonappetito.DTOs.RistoranteDTO;
//import it.jdk.buonappetito.entities.Posto;
//import it.jdk.buonappetito.entities.Prenotazione;
//import it.jdk.buonappetito.entities.Ristorante;
//import it.jdk.buonappetito.entities.Utente;
//import it.jdk.buonappetito.repositories.PostoRepository;
//import it.jdk.buonappetito.repositories.PrenotazioneRepository;
//import it.jdk.buonappetito.repositories.RistoranteRepository;
//import it.jdk.buonappetito.repositories.UtenteRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class PrenotazioneService {
//
//    private final PrenotazioneRepository prenotazioneRepository;
//    private final UtenteRepository utenteRepository;
//    private final RistoranteRepository ristoranteRepository;
//    private final PostoRepository postoRepository;
//
//    @Autowired
//    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository, UtenteRepository utenteRepository, RistoranteRepository ristoranteRepository, PostoRepository postoRepository) {
//        this.prenotazioneRepository = prenotazioneRepository;
//        this.utenteRepository = utenteRepository;
//        this.ristoranteRepository = ristoranteRepository;
//        this.postoRepository = postoRepository;
//    }
//
//    @Transactional
//    public PrenotazioneDTO prenota(PrenotazioneDTO prenotazioneDTO) {
//        Utente utente = utenteRepository.findById(prenotazioneDTO.getIdUtente())
//                .orElseThrow(() -> new EntityNotFoundException("Utente not found with ID: " + prenotazioneDTO.getIdUtente()));
//
//        Ristorante ristorante = ristoranteRepository.findById(prenotazioneDTO.getIdRistorante())
//                .orElseThrow(() -> new EntityNotFoundException("Ristorante not found with ID: " + prenotazioneDTO.getIdRistorante()));
//
//        LocalDateTime chiusura = prenotazioneDTO.getDataOraPrenotazione().withHour(ristorante.getOrarioChiusura().getHour()).minusHours(2);
//        if (prenotazioneDTO.getDataOraPrenotazione().isAfter(chiusura)) {
//            throw new IllegalArgumentException("Le prenotazioni possono essere accettate fino a 2 ore prima della chiusura del ristorante.");
//        }
//
//        // Controlla la disponibilità dei posti
//        List<Posto> posti = postoRepository.findAllById(prenotazioneDTO.getIdPosti());
//        if (posti.size() != prenotazioneDTO.getIdPosti().size()) {
//            throw new IllegalArgumentException("Alcuni dei posti richiesti non sono disponibili.");
//        }
//
//        Prenotazione prenotazione = Prenotazione.builder()
//                .utente(utente)
//                .ristorante(ristorante)
//                .dataOraPrenotazione(prenotazioneDTO.getDataOraPrenotazione())
//                .posti(posti)
//                .pagato(false)
//                .build();
//
//        prenotazione = prenotazioneRepository.save(prenotazione);
//        prenotazioneDTO.setId(prenotazione.getId());
//        return prenotazioneDTO;
//    }
//
//    @Transactional
//    public void cancellaPrenotazione(Long prenotazioneId) {
//        Prenotazione prenotazione = prenotazioneRepository.findById(prenotazioneId)
//                .orElseThrow(() -> new EntityNotFoundException("Prenotazione not found with ID: " + prenotazioneId));
//        prenotazioneRepository.delete(prenotazione);
//    }
//
//    @Transactional
//    public void chiudiConto(Long prenotazioneId) {
//        Prenotazione prenotazione = prenotazioneRepository.findById(prenotazioneId)
//                .orElseThrow(() -> new EntityNotFoundException("Prenotazione not found with ID: " + prenotazioneId));
//        prenotazione.setPagato(true);
//        prenotazioneRepository.save(prenotazione);
//    }
//
//    public List<RistoranteDTO> cercaRistorantiPerCitta(Long utenteId, String citta) {
//        Utente utente = utenteRepository.findById(utenteId)
//                .orElseThrow(() -> new EntityNotFoundException("Utente not found with ID: " + utenteId));
//        List<Ristorante> ristoranti = ristoranteRepository.findByComuneNome(citta);
//        return ristoranti.stream().map(ristorante -> RistoranteDTO.builder()
//                .id(ristorante.getId())
//                .nome(ristorante.getNome())
//                .indirizzo(ristorante.getIndirizzo())
//                .idComune(ristorante.getComune().getId())
//                .idProprietario(ristorante.getProprietario().getId())
//                .orarioApertura(ristorante.getOrarioApertura())
//                .orarioChiusura(ristorante.getOrarioChiusura())
//                .build()).collect(Collectors.toList());
//    }
//}
