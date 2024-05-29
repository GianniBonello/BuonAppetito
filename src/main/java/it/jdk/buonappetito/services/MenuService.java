package it.jdk.buonappetito.services;

import it.jdk.buonappetito.DTOs.MenuDTO;
import it.jdk.buonappetito.entities.Menu;
import it.jdk.buonappetito.entities.Pietanza;
import it.jdk.buonappetito.repositories.MenuRepository;
import it.jdk.buonappetito.repositories.PietanzaRepository;
import it.jdk.buonappetito.repositories.RistoranteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final PietanzaRepository pietanzaRepository;
    private final RistoranteRepository ristoranteRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, PietanzaRepository pietanzaRepository, RistoranteRepository ristoranteRepository) {
        this.menuRepository = menuRepository;
        this.pietanzaRepository = pietanzaRepository;
        this.ristoranteRepository = ristoranteRepository;
    }

    public MenuDTO create(MenuDTO menuDTO) {
        Menu menu = Menu.builder()
                .pietanze(pietanzaRepository.findAllById(menuDTO.getIdPietanze()))
                .ristorante(ristoranteRepository.getReferenceById(menuDTO.getIdRistorante()))
                .build();
        menuRepository.saveAndFlush(menu);
        menuDTO.setId(menu.getId());
        return menuDTO;
    }

    //    public MenuDTO update(MenuDTO menuDTO) {
//        Menu menu = menuRepository.getReferenceById(menuDTO.getId());
//        menu.setPietanze(pietanzaRepository.findAllById(menuDTO.getIdPietanze()));
//        menu.setRistorante(ristoranteRepository.getReferenceById(menuDTO.getIdRistorante()));
//        menuRepository.saveAndFlush(menu);
//        return menuDTO;
//    }
    @Transactional
    public MenuDTO update(MenuDTO menuDTO) {
        Menu menu = menuRepository.findById(menuDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Menu not found with ID: " + menuDTO.getId()));

        // Aggiorna le pietanze del menu
        menu.setPietanze(pietanzaRepository.findAllById(menuDTO.getIdPietanze()));

        menuRepository.saveAndFlush(menu);
        menuDTO.setIdRistorante(menu.getRistorante().getId());
        return menuDTO;
    }

    public MenuDTO findById(Long id) {
        Menu menu = menuRepository.getReferenceById(id);
        return MenuDTO.builder()
                .id(menu.getId())
                .idPietanze(menu.getPietanze().stream()
                        .map(Pietanza::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    public List<MenuDTO> findAll() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream().map(menu ->
                        MenuDTO.builder()
                                .idPietanze(menu.getPietanze().stream().map(Pietanza::getId).toList())
                                .idRistorante(menu.getRistorante().getId())
                                .build())
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        menuRepository.deleteById(id);
    }
}
