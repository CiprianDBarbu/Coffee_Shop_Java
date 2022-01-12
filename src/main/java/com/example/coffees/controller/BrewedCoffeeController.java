package com.example.coffees.controller;

import com.example.coffees.model.BrewedCoffee;
import com.example.coffees.service.BrewedCoffeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brewed_coffee")
public class BrewedCoffeeController {

    private final BrewedCoffeeService brewedCoffeeService;

    public BrewedCoffeeController(BrewedCoffeeService brewedCoffeeService) {
        this.brewedCoffeeService = brewedCoffeeService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<BrewedCoffee>> retrieveBrewedCoffees() {
        return ResponseEntity.ok()
                .body(brewedCoffeeService.retrieveBrewedCoffees());
    }

    @PostMapping("/new")
    public ResponseEntity<BrewedCoffee> saveNewBrewedCoffee(@RequestBody BrewedCoffee brewedCoffee,
                                                            @RequestParam int coffeeId,
                                                            @RequestParam int syrupId) {
        return ResponseEntity.ok()
                .body(brewedCoffeeService.saveNewBrewedCoffee(brewedCoffee, coffeeId, syrupId));
    }

    @DeleteMapping("/{brewedCoffeeId}")
    public ResponseEntity<String> deleteBrewedCoffeeById(@PathVariable int coffeeId) {
        return ResponseEntity.ok()
                .body(brewedCoffeeService.deleteBrewedCoffeeById(coffeeId));
    }

    @GetMapping("/list/{coffeeId}")
    public ResponseEntity<List<BrewedCoffee>> findBrewedCoffeeByCoffeeName(@RequestBody String coffeeId) {
        return ResponseEntity.ok()
                .body(brewedCoffeeService.findBrewedCoffeeByCoffeeName(coffeeId));
    }
}
