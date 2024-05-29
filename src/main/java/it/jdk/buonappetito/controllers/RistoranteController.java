package it.jdk.buonappetito.controllers;

import it.jdk.buonappetito.DTOs.RistoranteDTO;
import it.jdk.buonappetito.services.RistoranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ristorante")
public class RistoranteController {

    private final RistoranteService ristoranteService;

    @Autowired
    public RistoranteController(RistoranteService ristoranteService) {
        this.ristoranteService = ristoranteService;
    }

    @PostMapping("/create")
    public ResponseEntity<RistoranteDTO> create (@RequestBody RistoranteDTO ristorante) {
        RistoranteDTO created = ristoranteService.create(ristorante);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update")
    public ResponseEntity<RistoranteDTO> update (@RequestBody RistoranteDTO ristorante) {
        RistoranteDTO updated = ristoranteService.update(ristorante);
        return new ResponseEntity<>(updated, HttpStatus.CREATED);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<RistoranteDTO> findById (@PathVariable Long id) {
        RistoranteDTO ristorante = ristoranteService.findById(id);
        return new ResponseEntity<>(ristorante, HttpStatus.OK);
    }

    @GetMapping("/findALl")
    public ResponseEntity<List<RistoranteDTO>> findAll () {
        return new ResponseEntity<>(ristoranteService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        return new ResponseEntity<>("Ristorante con id: "+id+" eliminato con successo.", HttpStatus.OK);
    }
}
