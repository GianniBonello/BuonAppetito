package it.jdk.buonappetito.controllers;

import it.jdk.buonappetito.DTOs.PostoDTO;
import it.jdk.buonappetito.services.PostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posto")
public class PostoController {

    private final PostoService postoService;

    @Autowired
    public PostoController(PostoService postoService) {
        this.postoService = postoService;
    }

    @PostMapping("/create")
    public ResponseEntity<PostoDTO> createPosto(@RequestBody PostoDTO postoDTO) {
        return new ResponseEntity<>(postoService.createPosto(postoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<PostoDTO> updatePosto(@RequestBody PostoDTO postoDTO) {
        return ResponseEntity.ok(postoService.updatePosto(postoDTO));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<PostoDTO> getPostoById(@PathVariable Long id) {
        return ResponseEntity.ok(postoService.getPostoById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PostoDTO>> getAllPostos() {
        return ResponseEntity.ok(postoService.getAllPostos());
    }
}
