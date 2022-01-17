package com.example.coffees.controller;

import com.example.coffees.model.Coffee;
import com.example.coffees.service.CoffeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Coffee>> retrieveCoffees() {
        return ResponseEntity.ok()
                .body(coffeeService.retrieveCoffees());
    }

    @PostMapping("/new")
    public ResponseEntity<Coffee> saveNewCoffee(@RequestBody Coffee coffee) {
        return ResponseEntity.created(URI.create("/coffee" + coffee.getCoffeeId()))
                .body(coffeeService.saveNewCoffee(coffee));
    }

    @PutMapping("/{coffeeId}")
    public ResponseEntity<Coffee> editCoffee(@PathVariable int coffeeId,
                                             @RequestBody Coffee coffee) {
        return ResponseEntity.created(URI.create("/coffee" + coffeeId))
                .body(coffeeService.editCoffee(coffeeId, coffee));
    }

    @DeleteMapping("/{coffeeId}")
    public ResponseEntity<String> deleteCoffeeById(@PathVariable int coffeeId) {
        return ResponseEntity.ok()
                .body(coffeeService.deleteCoffeeById(coffeeId));
    }
}
