package it.jdk.buonappetito.services;

import it.jdk.buonappetito.DTOs.PietanzaDTO;
import it.jdk.buonappetito.entities.Pietanza;
import it.jdk.buonappetito.repositories.PietanzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PietanzaService {

    private final PietanzaRepository pietanzaRepository;

    @Autowired
    public PietanzaService(PietanzaRepository pietanzaRepository) {
        this.pietanzaRepository = pietanzaRepository;
    }

    public PietanzaDTO create (PietanzaDTO pietanzaDTO) {
        Pietanza pietanza = Pietanza.builder()
                .nome(pietanzaDTO.getNome())
                .prezzo(pietanzaDTO.getPrezzo())
                .build();
        pietanzaRepository.saveAndFlush(pietanza);
        pietanzaDTO.setId(pietanza.getId());
        return pietanzaDTO;
    }

    public PietanzaDTO update(PietanzaDTO pietanzaDTO) {
        Pietanza pietanza = pietanzaRepository.getReferenceById(pietanzaDTO.getId());
        pietanza.setNome(pietanzaDTO.getNome());
        pietanza.setPrezzo(pietanzaDTO.getPrezzo());
        pietanzaRepository.saveAndFlush(pietanza);
        pietanzaDTO.setId(pietanza.getId());
        return pietanzaDTO;
    }

    public List<PietanzaDTO> getAll() {
        List<Pietanza> pietanze = pietanzaRepository.findAll();
        return pietanze.stream()
                .map(pietanza -> PietanzaDTO
                        .builder()
                        .nome(pietanza.getNome())
                        .prezzo(pietanza.getPrezzo())
                        .build())
                .collect(Collectors.toList());
    }

//    public List<PietanzaDTO> findAllByMenuId(Long id) {
//        List<Pietanza> pietanzeNelMenu = pietanzaRepository.findAll
//    }

    public PietanzaDTO getById(Long id) {
        Pietanza pietanza = pietanzaRepository.getReferenceById(id);
        return PietanzaDTO.builder()
                .nome(pietanza.getNome())
                .prezzo(pietanza.getPrezzo())
                .build();
    }

    public void delete(Long id) {
        pietanzaRepository.deleteById(id);
    }
}
