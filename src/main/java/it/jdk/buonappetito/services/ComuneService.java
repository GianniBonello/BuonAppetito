package it.jdk.buonappetito.services;

import it.jdk.buonappetito.DTOs.ComuneDTO;
import it.jdk.buonappetito.entities.Comune;
import it.jdk.buonappetito.repositories.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComuneService {

    private final ComuneRepository comuneRepository;

    @Autowired
    public ComuneService(ComuneRepository comuneRepository) {
        this.comuneRepository = comuneRepository;
    }

    public ComuneDTO create(ComuneDTO comuneDTO) {
        Comune daCreare = Comune
                .builder()
                .nomeComune(comuneDTO.getNomeComune())
                .build();
        comuneRepository.saveAndFlush(daCreare);
        comuneDTO.setId(daCreare.getId());
        return comuneDTO;
    }

    public ComuneDTO getComuneById(Long id){
        Comune comune = comuneRepository.getReferenceById(id);
        return ComuneDTO
                .builder()
                .nomeComune(comune.getNomeComune())
                .build();
    }

    public List<ComuneDTO> getAllComuni() {
        List<Comune> comunes = comuneRepository.findAll();
        return comunes.stream()
                .map(comune -> ComuneDTO.builder()
                        .id(comune.getId())
                        .nomeComune(comune.getNomeComune())
                        .build())
                .collect(Collectors.toList());
    }

    public ComuneDTO update(ComuneDTO comuneDTO) {
        Comune comune = comuneRepository.getReferenceById(comuneDTO.getId());
        comune.setNomeComune(comuneDTO.getNomeComune());
        comuneRepository.saveAndFlush(comune);
        return comuneDTO;
    }

    public void delete(Long id) {
        comuneRepository.deleteById(id);
    }
}
