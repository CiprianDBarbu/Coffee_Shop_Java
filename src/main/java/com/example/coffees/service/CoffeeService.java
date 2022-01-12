package com.example.coffees.service;

import com.example.coffees.exceptions.NoElementFoundException;
import com.example.coffees.model.Coffee;
import com.example.coffees.repository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public Coffee saveNewCoffee(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    public List<Coffee> retrieveCoffees() {
        return coffeeRepository.findAll();
    }

    public String deleteCoffeeById(int coffeeId) {
        coffeeRepository.deleteById(coffeeId);
        return "OK!";
    }

    public Coffee editCoffee(int coffeeId, Coffee coffee) {
        Coffee actualCoffee = coffeeRepository.findById(coffeeId)
                .orElseThrow(() -> new NoElementFoundException("Id not available!"));

        actualCoffee.setName(coffee.getName());
        actualCoffee.setPrice(coffee.getPrice());

        return coffeeRepository.save(actualCoffee);
    }
}
