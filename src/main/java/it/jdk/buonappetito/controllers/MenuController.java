package it.jdk.buonappetito.controllers;

import it.jdk.buonappetito.DTOs.MenuDTO;
import it.jdk.buonappetito.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/create")
    public ResponseEntity<MenuDTO> createMenu(@RequestBody MenuDTO menu) {
        return new ResponseEntity<>(menuService.create(menu), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<MenuDTO> updateMenu(@RequestBody MenuDTO menu) {
        return new ResponseEntity<>(menuService.update(menu), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<MenuDTO> getMenuById(@PathVariable Long id) {
        return new ResponseEntity<>(menuService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MenuDTO>> getAllMenus() {
        return new ResponseEntity<>(menuService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable Long id) {
        menuService.delete(id);
        return new ResponseEntity<>("Menu con id: "+id+" cancellato correttamente.", HttpStatus.OK);
    }
}
