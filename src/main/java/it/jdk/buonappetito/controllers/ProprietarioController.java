package it.jdk.buonappetito.controllers;

import it.jdk.buonappetito.DTOs.ProprietarioDTO;
import it.jdk.buonappetito.services.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {

    private final ProprietarioService proprietarioService;

    @Autowired
    public ProprietarioController(ProprietarioService proprietarioService) {
        this.proprietarioService = proprietarioService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProprietarioDTO> create(@RequestBody ProprietarioDTO proprietario) {
        return new ResponseEntity<>(proprietarioService.create(proprietario), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ProprietarioDTO> update(@RequestBody ProprietarioDTO proprietario) {
        return new ResponseEntity<>(proprietarioService.update(proprietario), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ProprietarioDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(proprietarioService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProprietarioDTO>> getAll() {
        return new ResponseEntity<>(proprietarioService.getAllProprietari(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        proprietarioService.deleteById(id);
        return new ResponseEntity<>("Proprietario con id: " + id + " eliminato correttamente.", HttpStatus.OK);
    }
}
