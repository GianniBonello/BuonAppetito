package it.jdk.buonappetito.services;

import it.jdk.buonappetito.DTOs.PostoDTO;
import it.jdk.buonappetito.entities.Posto;
import it.jdk.buonappetito.entities.Ristorante;
import it.jdk.buonappetito.repositories.PostoRepository;
import it.jdk.buonappetito.repositories.RistoranteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostoService {

    private final PostoRepository postoRepository;
    private final RistoranteRepository ristoranteRepository;

    @Autowired
    public PostoService(PostoRepository postoRepository, RistoranteRepository ristoranteRepository) {
        this.postoRepository = postoRepository;
        this.ristoranteRepository = ristoranteRepository;
    }

    //    public PostoDTO createPosto(PostoDTO postoDTO) {
//        Posto posto = Posto.builder()
//                .ristorante(ristoranteRepository.getReferenceById(postoDTO.getIdRistorante()))
//                .build();
//        postoRepository.saveAndFlush(posto);
//        postoDTO.setId(posto.getId());
//        return postoDTO;
//    }
    @Transactional
    public PostoDTO createPosto(PostoDTO postoDTO) {
        Ristorante ristorante = ristoranteRepository.findById(postoDTO.getIdRistorante())
                .orElseThrow(() -> new EntityNotFoundException("Ristorante not found with ID: " + postoDTO.getIdRistorante()));

        Posto posto = Posto.builder()
                .ristorante(ristorante)
                .build();

        posto = postoRepository.saveAndFlush(posto);

        // Aggiungi il nuovo posto alla lista dei posti del ristorante
        ristorante.getPosti().add(posto);
        ristoranteRepository.saveAndFlush(ristorante);

        postoDTO.setId(posto.getId());
        return postoDTO;
    }

    public PostoDTO updatePosto(PostoDTO postoDTO) {
        Posto posto = postoRepository.getReferenceById(postoDTO.getId());
        posto.setRistorante(ristoranteRepository.getReferenceById(postoDTO.getIdRistorante()));
        postoRepository.saveAndFlush(posto);
        return postoDTO;
    }

    public PostoDTO getPostoById(Long id) {
        Posto posto = postoRepository.getReferenceById(id);
        return PostoDTO.builder().id(posto.getId()).idRistorante(posto.getRistorante().getId()).build();
    }

    public List<PostoDTO> getAllPostos() {
        List<Posto> postos = postoRepository.findAll();
        return postos.stream().map(posto -> PostoDTO
                        .builder()
                        .id(posto.getId())
                        .idRistorante(posto.getRistorante().getId())
                        .build())
                .collect(Collectors.toList());
    }
}
