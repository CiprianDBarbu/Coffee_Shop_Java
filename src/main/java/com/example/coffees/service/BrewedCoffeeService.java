package com.example.coffees.service;

import com.example.coffees.exceptions.NoElementFoundException;
import com.example.coffees.model.BrewedCoffee;
import com.example.coffees.model.Coffee;
import com.example.coffees.model.Syrup;
import com.example.coffees.repository.BrewedCoffeeRepository;
import com.example.coffees.repository.CoffeeRepository;
import com.example.coffees.repository.SyrupRepository;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.List;

@Service
public class BrewedCoffeeService {
    private final BrewedCoffeeRepository brewedCoffeeRepository;
    private final CoffeeRepository coffeeRepository;
    private final SyrupRepository syrupRepository;

    public BrewedCoffeeService(BrewedCoffeeRepository brewedCoffeeRepository, CoffeeRepository coffeeRepository, SyrupRepository syrupRepository) {
        this.brewedCoffeeRepository = brewedCoffeeRepository;
        this.coffeeRepository = coffeeRepository;
        this.syrupRepository = syrupRepository;
    }

    public List<BrewedCoffee> retrieveBrewedCoffees() {
        return brewedCoffeeRepository.findAll();
    }

    public BrewedCoffee saveNewBrewedCoffee(BrewedCoffee brewedCoffee, int coffeeId, int syrupId) {
        Coffee coffee = coffeeRepository.findById(coffeeId)
                .orElseThrow(() -> new NoElementFoundException("Id is not a valid one!"));

        Syrup syrup = syrupRepository.findById(syrupId)
                .orElseThrow(() -> new NoElementFoundException("Id is not a valid one!"));

        brewedCoffee.setCoffee(coffee);
        brewedCoffee.setSyrup(syrup);

        return brewedCoffeeRepository.save(brewedCoffee);
    }

    public String deleteBrewedCoffeeById(int coffeeId) {
        brewedCoffeeRepository.deleteById(coffeeId);
        return "Deleted successfully!";
    }

    public List<BrewedCoffee> findBrewedCoffeeByCoffeeName(String coffeeName) {
        return brewedCoffeeRepository.findBrewedCoffeeByCoffeeName(coffeeName);
    }
}
