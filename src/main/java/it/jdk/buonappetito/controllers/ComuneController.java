package it.jdk.buonappetito.controllers;

import it.jdk.buonappetito.DTOs.ComuneDTO;
import it.jdk.buonappetito.services.ComuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comune")
public class ComuneController {

    private final ComuneService comuneService;

    @Autowired
    public ComuneController(ComuneService comuneService) {
        this.comuneService = comuneService;
    }

    @PostMapping("/create")
    public ResponseEntity<ComuneDTO> createComune(@RequestBody ComuneDTO comuneDTO) {
        return new ResponseEntity<>(comuneService.create(comuneDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<ComuneDTO> getComuneById(@PathVariable Long id) {
        return new ResponseEntity<>(comuneService.getComuneById(id), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ComuneDTO>> getAllComune() {
        return new ResponseEntity<>(comuneService.getAllComuni(), HttpStatus.OK);
    }

    @PutMapping("/update/")
    public ResponseEntity<ComuneDTO> updateComune(@RequestBody ComuneDTO comuneDTO) {
        return new ResponseEntity<>(comuneService.update(comuneDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComune(@PathVariable Long id) {
        comuneService.delete(id);
        return new ResponseEntity<>("Comune con id :" + id + " eliminato.", HttpStatus.OK);
    }
}
