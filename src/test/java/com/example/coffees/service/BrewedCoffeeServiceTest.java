package com.example.coffees.service;

import com.example.coffees.model.BrewedCoffee;
import com.example.coffees.model.Coffee;
import com.example.coffees.model.Syrup;
import com.example.coffees.repository.BrewedCoffeeRepository;
import com.example.coffees.repository.CoffeeRepository;
import com.example.coffees.repository.SyrupRepository;
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
public class BrewedCoffeeServiceTest {

    @InjectMocks
    private BrewedCoffeeService brewedCoffeeService;

    @Mock
    private BrewedCoffeeRepository brewedCoffeeRepository;
    @Mock
    private CoffeeRepository coffeeRepository;
    @Mock
    private SyrupRepository syrupRepository;

    @Test
    @DisplayName("Saving new brewed coffee in a happy flow")
    void saveNewBrewedCoffeeHappyFlow() {
        //arrange
        Coffee coffee = new Coffee("CoffeeTest", 1.2);
        Syrup syrup = new Syrup("SyrupTest", 0.2);
        BrewedCoffee brewedCoffee = new BrewedCoffee("BrewedCoffeeTest",true, coffee, syrup);
        when(coffeeRepository.findById(coffee.getCoffeeId())).thenReturn(Optional.of(coffee));
        when(syrupRepository.findById(syrup.getSyrupId())).thenReturn(Optional.of(syrup));
        when(brewedCoffeeRepository.save(brewedCoffee)).thenReturn(brewedCoffee);
        //act
        BrewedCoffee result = brewedCoffeeService.saveNewBrewedCoffee(brewedCoffee, coffee.getCoffeeId(), syrup.getSyrupId());
        //assert
        assertNotNull(result);

        assertEquals(brewedCoffee.getCoffee().getCoffeeId(), result.getCoffee().getCoffeeId());
        assertEquals(brewedCoffee.getCoffee().getName(), result.getCoffee().getName());
        assertEquals(brewedCoffee.getCoffee().getPrice(), result.getCoffee().getPrice());

        assertEquals(brewedCoffee.getSyrup().getSyrupId(), result.getSyrup().getSyrupId());
        assertEquals(brewedCoffee.getSyrup().getName(), brewedCoffee.getSyrup().getName());
        assertEquals(brewedCoffee.getSyrup().getPrice(), brewedCoffee.getSyrup().getPrice());

        assertEquals(brewedCoffee.getBrewedCoffeeId(), result.getBrewedCoffeeId());
        assertEquals(brewedCoffee.getName(), result.getName());
        assertEquals(brewedCoffee.getFinalPrice(), result.getFinalPrice());
    }

    @Test
    @DisplayName("Retrieving brewed coffees in a happy flow")
    void retrievingBrewedCoffeesHappyFlow() {
        //arrange
        List<BrewedCoffee> brewedCoffeeList = new ArrayList<>();
        Coffee coffee = new Coffee("CoffeeTest", 1.2);
        Syrup syrup = new Syrup("SyrupTest", 0.2);
        BrewedCoffee brewedCoffee = new BrewedCoffee("BrewedCoffeeTest",true, coffee, syrup);
        brewedCoffeeList.add(brewedCoffee);
        when(brewedCoffeeRepository.findAll()).thenReturn(brewedCoffeeList);
        //act
        List<BrewedCoffee> brewedCoffeeListResult = brewedCoffeeService.retrieveBrewedCoffees();
        //assert
        assertNotNull(brewedCoffeeList);
        assertEquals(1, brewedCoffeeList.size());
        assertEquals(brewedCoffeeList, brewedCoffeeListResult);
    }

    @Test
    @DisplayName("Finding brewed coffee by coffee name in a happy flow")
    void findBrewedCoffeeByIdHappyFlow() {
        //arrange
        List<BrewedCoffee> brewedCoffeeList = new ArrayList<>();
        Coffee coffee = new Coffee("CoffeeTest", 1.2);
        Syrup syrup = new Syrup("SyrupTest", 0.2);
        BrewedCoffee brewedCoffee = new BrewedCoffee("BrewedCoffeeTest",true, coffee, syrup);
        brewedCoffeeList.add(brewedCoffee);
        when(brewedCoffeeRepository.findBrewedCoffeeByCoffeeName(coffee.getName())).thenReturn(brewedCoffeeList);
        //act
        List<BrewedCoffee> brewedCoffeeListResult = brewedCoffeeService.findBrewedCoffeeByCoffeeName(coffee.getName());
        //assert
        assertNotNull(brewedCoffeeList);
        assertEquals(1, brewedCoffeeList.size());
        assertEquals(brewedCoffeeList.get(0).getName(), brewedCoffeeListResult.get(0).getName());
    }

    @Test
    @DisplayName("Delete brewed coffee happy flow")
    void deleteBrewedCoffeeHappyFlow() {
        brewedCoffeeService.deleteBrewedCoffeeById(0);
        verify(brewedCoffeeRepository).deleteById(0);
    }
}
