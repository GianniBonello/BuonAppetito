package it.jdk.buonappetito.services;

import it.jdk.buonappetito.DTOs.RistoranteDTO;
import it.jdk.buonappetito.entities.Posto;
import it.jdk.buonappetito.entities.Proprietario;
import it.jdk.buonappetito.entities.Ristorante;
import it.jdk.buonappetito.entities.Utente;
import it.jdk.buonappetito.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RistoranteService {

    private final RistoranteRepository ristoranteRepository;
    private final ProprietarioRepository proprietarioRepository;
    private final PostoRepository postoRepository;
    private final UtenteRepository utenteRepository;
    private final ComuneRepository comuneRepository;

    @Autowired
    public RistoranteService(RistoranteRepository ristoranteRepository, ProprietarioRepository proprietarioRepository, PostoRepository postoRepository, UtenteRepository utenteRepository, ComuneRepository comuneRepository) {
        this.ristoranteRepository = ristoranteRepository;
        this.proprietarioRepository = proprietarioRepository;
        this.postoRepository = postoRepository;
        this.utenteRepository = utenteRepository;
        this.comuneRepository = comuneRepository;
    }

//    public RistoranteDTO create (RistoranteDTO ristoranteDTO) {
//        Ristorante ristorante = Ristorante.builder()
//                .nome(ristoranteDTO.getNome())
//                .indirizzo(ristoranteDTO.getIndirizzo())
//                .comune(proprietarioRepository.getReferenceById(ristoranteDTO.getIdProprietario()).getComune())
//                .proprietario(proprietarioRepository.getReferenceById(ristoranteDTO.getIdProprietario()))
//                .posti(postoRepository.findAllById(ristoranteDTO.getIdPosti()))
//                .orarioApertura(ristoranteDTO.getOrarioApertura())
//                .orarioChiusura(ristoranteDTO.getOrarioChiusura())
//                .build();
//        ristoranteRepository.saveAndFlush(ristorante);
//        ristoranteDTO.setId(ristorante.getId());
//        return ristoranteDTO;
//    }


    @Transactional
    public RistoranteDTO create(RistoranteDTO ristoranteDTO) {
        Optional<Utente> utenteOpt = utenteRepository.findById(ristoranteDTO.getIdProprietario());

        if (utenteOpt.isEmpty()) {
            throw new EntityNotFoundException("Utente not found with ID: " + ristoranteDTO.getIdProprietario());
        }

        Utente utente = utenteOpt.get();

        Proprietario proprietario = new Proprietario();
        proprietario.setId(utente.getId());
        proprietario.setNome(utente.getNome());
        proprietario.setCognome(utente.getCognome());
        proprietario.setDataDiNascita(utente.getDataDiNascita());
        proprietario.setComune(utente.getComune());
        proprietario.setIndirizzo(utente.getIndirizzo());
        proprietario.setEmail(utente.getEmail());
        proprietario.setPassword(utente.getPassword());

        Proprietario savedProprietario = utenteRepository.save(proprietario);

        Ristorante ristorante = Ristorante.builder()
                .nome(ristoranteDTO.getNome())
                .indirizzo(ristoranteDTO.getIndirizzo())
                .comune(comuneRepository.getReferenceById(utente.getComune().getId()))
                .proprietario(savedProprietario)
                .posti(postoRepository.findAllById(ristoranteDTO.getIdPosti()))
                .orarioApertura(ristoranteDTO.getOrarioApertura())
                .orarioChiusura(ristoranteDTO.getOrarioChiusura())
                .build();

        List<Posto> posti = ristoranteDTO.getIdPosti().stream()
                .map(idPosto -> {
                    Posto posto = new Posto();
                    posto.setId(idPosto);
                    posto.setRistorante(ristorante);
                    return posto;
                })
                .collect(Collectors.toList());

        ristorante.setPosti(posti);

        Ristorante savedRistorante = ristoranteRepository.save(ristorante);

        savedProprietario.setRistorante(savedRistorante);
        utenteRepository.save(savedProprietario);

        return new RistoranteDTO(
                savedRistorante.getId(),
                savedRistorante.getNome(),
                savedRistorante.getIndirizzo(),
                savedRistorante.getComune() != null ? savedRistorante.getComune().getId() : null,
                savedProprietario.getId(),
                savedRistorante.getPosti() != null ? savedRistorante.getPosti().stream().map(Posto::getId).collect(Collectors.toList()) : null,
                savedRistorante.getOrarioApertura(),
                savedRistorante.getOrarioChiusura()
        );
    }


    public RistoranteDTO update(RistoranteDTO ristoranteDTO) {
        Ristorante ristorante = ristoranteRepository.getReferenceById(ristoranteDTO.getId());

        ristorante.setNome(ristoranteDTO.getNome());
        ristorante.setIndirizzo(ristoranteDTO.getIndirizzo());
        ristorante.setComune(proprietarioRepository.getReferenceById(ristoranteDTO.getIdProprietario()).getComune());
        ristorante.setProprietario(proprietarioRepository.getReferenceById(ristoranteDTO.getIdProprietario()));
        ristorante.setPosti(postoRepository.findAllById(ristoranteDTO.getIdPosti()));
        ristorante.setOrarioApertura(ristoranteDTO.getOrarioApertura());
        ristorante.setOrarioChiusura(ristoranteDTO.getOrarioChiusura());

        ristoranteRepository.saveAndFlush(ristorante);
        return ristoranteDTO;
    }

    public RistoranteDTO findById(Long id) {
        Ristorante ristorante = ristoranteRepository.getReferenceById(id);
        return RistoranteDTO.builder()
                .nome(ristorante.getNome())
                .indirizzo(ristorante.getIndirizzo())
                .idComune(ristorante.getComune().getId())
                .idProprietario(ristorante.getProprietario().getId())
                .idPosti(ristorante.getPosti().stream()
                        .map(Posto::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    public List<RistoranteDTO> findAll() {
        List<Ristorante> ristoranti = ristoranteRepository.findAll();
        return ristoranti.stream().map(ristorante ->
                        RistoranteDTO.builder()
                                .id(ristorante.getId())
                                .nome(ristorante.getNome())
                                .indirizzo(ristorante.getIndirizzo())
                                .idComune(ristorante.getComune().getId())
                                .idProprietario(ristorante.getProprietario().getId())
                                .orarioApertura(ristorante.getOrarioApertura())
                                .orarioChiusura(ristorante.getOrarioChiusura())
                                .build())
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        ristoranteRepository.deleteById(id);
    }
}
