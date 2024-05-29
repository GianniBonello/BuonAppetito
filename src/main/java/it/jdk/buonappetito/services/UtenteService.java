package it.jdk.buonappetito.services;

import it.jdk.buonappetito.DTOs.ComuneDTO;
import it.jdk.buonappetito.DTOs.UtenteDTO;
import it.jdk.buonappetito.entities.Comune;
import it.jdk.buonappetito.entities.Utente;
import it.jdk.buonappetito.repositories.ComuneRepository;
import it.jdk.buonappetito.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final ComuneRepository comuneRepository;

    @Autowired
    public UtenteService(UtenteRepository utenteRepository, ComuneRepository comuneRepository) {
        this.utenteRepository = utenteRepository;
        this.comuneRepository = comuneRepository;
    }

    public UtenteDTO create(UtenteDTO utenteDTO) {
        Utente utente = Utente
                .builder()
                .nome(utenteDTO.getNome())
                .cognome(utenteDTO.getCognome())
                .dataDiNascita(utenteDTO.getDataDiNascita())
                .comune(comuneRepository.getReferenceById(utenteDTO.getIdComune()))
                .indirizzo(utenteDTO.getIndirizzo())
                .email(utenteDTO.getEmail())
                .password(utenteDTO.getPassword())
                .build();
        utenteRepository.saveAndFlush(utente);
        utenteDTO.setId(utente.getId());
        return utenteDTO;
    }

    public UtenteDTO update(UtenteDTO utenteDTO) {
        Utente utente = utenteRepository.getReferenceById(utenteDTO.getId());
        utente.setNome(utenteDTO.getNome());
        utente.setCognome(utenteDTO.getCognome());
        utente.setDataDiNascita(utenteDTO.getDataDiNascita());
        utente.setComune(comuneRepository.getReferenceById(utenteDTO.getIdComune()));
        utente.setIndirizzo(utenteDTO.getIndirizzo());
        utente.setEmail(utenteDTO.getEmail());
        utente.setPassword(utenteDTO.getPassword());

        utenteRepository.saveAndFlush(utente);
        return utenteDTO;
    }

    public UtenteDTO getUtenteById(Long id){
        Utente utente = utenteRepository.getReferenceById(id);
        return UtenteDTO
                .builder()
                .id(utente.getId())
                .nome(utente.getNome())
                .cognome(utente.getCognome())
                .dataDiNascita(utente.getDataDiNascita())
                .idComune(utente.getComune().getId())
                .indirizzo(utente.getIndirizzo())
                .email(utente.getEmail())
                .password(utente.getPassword())
                .build();
    }

    public List<UtenteDTO> getAllUtenti(){
        List<Utente> utenti = utenteRepository.findAll();
        return utenti.stream()
                .map(utente -> UtenteDTO.builder()
                        .id(utente.getId())
                        .nome(utente.getNome())
                        .cognome(utente.getCognome())
                        .dataDiNascita(utente.getDataDiNascita())
                        .idComune(utente.getComune().getId())
                        .indirizzo(utente.getIndirizzo())
                        .email(utente.getEmail())
                        .password(utente.getPassword())
                        .build())
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        utenteRepository.deleteById(id);
    }
}
