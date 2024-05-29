package it.jdk.buonappetito.controllers;

import it.jdk.buonappetito.DTOs.UtenteDTO;
import it.jdk.buonappetito.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    private final UtenteService utenteService;

    @Autowired
    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @PostMapping("/create")
    public ResponseEntity<UtenteDTO> create(@RequestBody UtenteDTO utenteDTO) {
        return new ResponseEntity<>(utenteService.create(utenteDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UtenteDTO> update(@RequestBody UtenteDTO utenteDTO) {
        return new ResponseEntity<>(utenteService.update(utenteDTO), HttpStatus.OK);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<UtenteDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(utenteService.getUtenteById(id), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<UtenteDTO>> getAll() {
        return new ResponseEntity<>(utenteService.getAllUtenti(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        utenteService.delete(id);
        return new ResponseEntity<>("Utente con id: " + id +" eliminato correttamente.", HttpStatus.OK);
    }
}
