package com.example.coffees.service;

import com.example.coffees.model.Coffee;
import com.example.coffees.repository.CoffeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CoffeeServiceTest {

    @InjectMocks
    private CoffeeService coffeeService;

    @Mock
    private CoffeeRepository coffeeRepository;

    @Test
    @DisplayName("Saving new coffee in a happy flow")
    void saveNewCoffeeHappyFlow() {
        //arrange
        Coffee coffee = new Coffee(1,"CoffeeTest", 1.20);
        when(coffeeRepository.save(coffee)).thenReturn(coffee);
        //act
        Coffee result = coffeeService.saveNewCoffee(coffee);
        //assert
        assertNotNull(result);
        assertEquals(coffee.getCoffeeId(), result.getCoffeeId());
        assertEquals(coffee.getName(), result.getName());
        assertEquals(coffee.getPrice(), result.getPrice());
    }

    @Test
    @DisplayName("Retrieving coffees in a happy flow")
    void retrieveCoffeesHappyFlow() {
        //arrange
        List<Coffee> coffeeList = new ArrayList<>();
        Coffee coffee = new Coffee(1,"CoffeeTest", 1.20);
        coffeeList.add(coffee);
        when(coffeeRepository.findAll()).thenReturn(coffeeList);
        //act
        List<Coffee> coffeeListResult = coffeeService.retrieveCoffees();
        //assert
        assertNotNull(coffeeListResult);
        assertEquals(1, coffeeListResult.size());
        assertEquals(coffeeList, coffeeListResult);
    }

    @Test
    @DisplayName("Editing coffee in a happy flow")
    void EditCoffeeHappyFlow() {
        //arrange
        Coffee coffeeOld = new Coffee(1,"CoffeeTestOld", 1.20);
        Coffee coffeeNew = new Coffee(1,"CoffeeTestNew", 2.20);
        when(coffeeRepository.findById(coffeeOld.getCoffeeId())).thenReturn(Optional.of(coffeeOld));
        when(coffeeRepository.save(coffeeOld)).thenReturn(coffeeNew);
        //act
        Coffee result = coffeeService.editCoffee(coffeeOld.getCoffeeId(),coffeeNew);
        //assert
        assertNotNull(result);
        assertEquals(coffeeNew.getCoffeeId(), result.getCoffeeId());
        assertEquals(coffeeNew.getName(), result.getName());
        assertEquals(coffeeNew.getPrice(), result.getPrice());
    }

    @Test
    @DisplayName("Deleting coffee in a happy flow")
    void deleteCoffeeHappyFlow() {
        //arrange
        String deletedCoffee = "OK!";
        //act
        String result = coffeeService.deleteCoffeeById(0);
        //assert
        assertEquals(deletedCoffee, result);
        verify(coffeeRepository).deleteById(0);
    }
}
