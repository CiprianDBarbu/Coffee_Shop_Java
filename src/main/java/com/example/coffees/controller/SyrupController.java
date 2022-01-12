package com.example.coffees.controller;

import com.example.coffees.model.Syrup;
import com.example.coffees.service.SyrupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/syrup")
public class SyrupController {

    private final SyrupService syrupService;

    public SyrupController(SyrupService syrupService) {
        this.syrupService = syrupService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Syrup>> retrieveSyrups() {
        return ResponseEntity.ok()
                .body(syrupService.retrieveSyrups());
    }

    @PostMapping("/new")
    public ResponseEntity<Syrup> saveNewSyrup(@RequestBody Syrup syrup) {
        return ResponseEntity.ok()
                .body(syrupService.saveNewSyrup(syrup));
    }

    @DeleteMapping("/{syrupId}")
    public ResponseEntity<String> deleteSyrupById(@PathVariable int syrupId) {
        return ResponseEntity.ok()
                .body(syrupService.deleteSyrupById(syrupId));
    }
}
