package com.example.coffees.repository;

import com.example.coffees.model.BrewedCoffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrewedCoffeeRepository extends JpaRepository<BrewedCoffee, Integer> {
    @Query(value = "SELECT * FROM demo.brewed_coffee JOIN demo.coffee ON demo.brewed_coffee.coffee_id=demo.coffee.coffee_id WHERE demo.coffee.coffee_name=:name", nativeQuery = true)
    List<BrewedCoffee> findBrewedCoffeeByCoffeeName(String name);
}
