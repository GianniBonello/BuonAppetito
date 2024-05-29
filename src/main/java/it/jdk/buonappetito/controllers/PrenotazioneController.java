//package it.jdk.buonappetito.controllers;
//
//import it.jdk.buonappetito.DTOs.PrenotazioneDTO;
//import it.jdk.buonappetito.DTOs.RistoranteDTO;
//import it.jdk.buonappetito.services.PrenotazioneService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/prenotazioni")
//public class PrenotazioneController {
//
//    private final PrenotazioneService prenotazioneService;
//
//    @Autowired
//    public PrenotazioneController(PrenotazioneService prenotazioneService) {
//        this.prenotazioneService = prenotazioneService;
//    }
//
//    @PostMapping("/prenota")
//    public ResponseEntity<PrenotazioneDTO> prenota(@RequestBody PrenotazioneDTO prenotazioneDTO) {
//        PrenotazioneDTO created = prenotazioneService.prenota(prenotazioneDTO);
//        return ResponseEntity.ok(created);
//    }
//
//    @DeleteMapping("/cancella/{id}")
//    public ResponseEntity<Void> cancellaPrenotazione(@PathVariable Long id) {
//        prenotazioneService.cancellaPrenotazione(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PutMapping("/chiudiconto/{id}")
//    public ResponseEntity<Void> chiudiConto(@PathVariable Long id) {
//        prenotazioneService.chiudiConto(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/cercaristoranti")
//    public ResponseEntity<List<RistoranteDTO>> cercaRistoranti(@RequestParam Long utenteId, @RequestParam String citta) {
//        List<RistoranteDTO> ristoranti = prenotazioneService.cercaRistorantiPerCitta(utenteId, citta);
//        return ResponseEntity.ok(ristoranti);
//    }
//}
