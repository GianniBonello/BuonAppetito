package it.jdk.buonappetito.controllers;

import it.jdk.buonappetito.DTOs.PietanzaDTO;
import it.jdk.buonappetito.services.PietanzaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pietanza")
public class PietanzaController {

    private final PietanzaService pietanzaService;

    @Autowired
    public PietanzaController(PietanzaService pietanzaService) {
        this.pietanzaService = pietanzaService;
    }

    @PostMapping("/create")
    public ResponseEntity<PietanzaDTO> createPietanza(@RequestBody PietanzaDTO pietanzaDTO) {
        return new ResponseEntity<>(pietanzaService.create(pietanzaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<PietanzaDTO> updatePietanza(@RequestBody PietanzaDTO pietanzaDTO) {
        return new ResponseEntity<>(pietanzaService.update(pietanzaDTO), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<PietanzaDTO> getPietanzaById(@PathVariable Long id) {
        return new ResponseEntity<>(pietanzaService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PietanzaDTO>> getAllPietanza() {
        return new ResponseEntity<>(pietanzaService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePietanza(@PathVariable Long id) {
        pietanzaService.delete(id);
        return new ResponseEntity<>("Pietanza con id: " + id + " eliminata", HttpStatus.OK);
    }
}
