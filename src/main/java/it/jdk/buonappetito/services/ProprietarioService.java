package it.jdk.buonappetito.services;

import it.jdk.buonappetito.DTOs.ProprietarioDTO;
import it.jdk.buonappetito.entities.Proprietario;
import it.jdk.buonappetito.repositories.ComuneRepository;
import it.jdk.buonappetito.repositories.ProprietarioRepository;
import it.jdk.buonappetito.repositories.RistoranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProprietarioService {

    private final ProprietarioRepository proprietarioRepository;
    private final ComuneRepository comuneRepository;
    private final RistoranteRepository ristoranteRepository;

    @Autowired
    public ProprietarioService(ProprietarioRepository proprietarioRepository, ComuneRepository comuneRepository, RistoranteRepository ristoranteRepository) {
        this.proprietarioRepository = proprietarioRepository;
        this.comuneRepository = comuneRepository;
        this.ristoranteRepository = ristoranteRepository;
    }

    public ProprietarioDTO create(ProprietarioDTO proprietarioDTO) {
        Proprietario proprietario = new Proprietario(ristoranteRepository.getReferenceById(proprietarioDTO.getIdRistorante()));
        proprietarioRepository.saveAndFlush(proprietario);
        proprietarioDTO.setId(proprietario.getId());
        return proprietarioDTO;
    }

    public ProprietarioDTO update(ProprietarioDTO proprietarioDTO) {
        Proprietario proprietario = proprietarioRepository.getReferenceById(proprietarioDTO.getId());
        proprietario.setNome(proprietarioDTO.getNome());
        proprietario.setCognome(proprietario.getCognome());
        proprietario.setDataDiNascita(proprietarioDTO.getDataDiNascita());
        proprietario.setComune(comuneRepository.getReferenceById(proprietarioDTO.getIdComune()));
        proprietario.setIndirizzo(proprietarioDTO.getIndirizzo());
        proprietario.setEmail(proprietarioDTO.getEmail());
        proprietario.setEmail(proprietarioDTO.getEmail());
        proprietarioRepository.saveAndFlush(proprietario);
        proprietarioDTO.setId(proprietario.getId());
        return proprietarioDTO;
    }

    public ProprietarioDTO getById(Long id) {
        Proprietario proprietario = proprietarioRepository.getReferenceById(id);
        ProprietarioDTO proprietarioDTO = new ProprietarioDTO();
        proprietarioDTO.setId(proprietario.getId());
        proprietarioDTO.setIdRistorante(proprietario.getRistorante().getId());
        return proprietarioDTO;
    }

    public List<ProprietarioDTO> getAllProprietari() {
        List<Proprietario> proprietari = proprietarioRepository.findAll();
        return proprietari.stream()
                .map(proprietario -> new ProprietarioDTO(
                        proprietario.getId(),
                        proprietario.getRistorante() != null ? proprietario.getRistorante().getId() : null))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        proprietarioRepository.deleteById(id);
    }
}