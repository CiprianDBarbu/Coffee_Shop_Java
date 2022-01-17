package com.example.coffees.service;

import com.example.coffees.exceptions.OrderNotEligibleForDiscountException;
import com.example.coffees.model.BrewedCoffee;
import com.example.coffees.model.Client;
import com.example.coffees.model.Coffee;
import com.example.coffees.model.ShopOrder;
import com.example.coffees.repository.BrewedCoffeeRepository;
import com.example.coffees.repository.ClientRepository;
import com.example.coffees.repository.ShopOrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShopOrderServiceTest {

    @InjectMocks
    private ShopOrderService shopOrderService;

    @Mock
    ShopOrderRepository shopOrderRepository;
    @Mock
    BrewedCoffeeRepository brewedCoffeeRepository;
    @Mock
    ClientRepository clientRepository;

    @Test
    @DisplayName("Saving new order in a happy flow")
    void saveNewOrderHappyFlow() {
        //arrange
        ShopOrder shopOrder = new ShopOrder();
        Client client = new Client("ClientTest");
        when(clientRepository.findById(client.getClientId())).thenReturn(Optional.of(client));
        when(shopOrderRepository.save(shopOrder)).thenReturn(shopOrder);
        //act
        ShopOrder result = shopOrderService.saveNewOrder(shopOrder, client.getClientId());
        //assert
        assertNotNull(result);
        assertEquals(shopOrder.getOrderId(), result.getOrderId());
    }

    @Test
    @DisplayName("Retrieving orders in a happy flow")
    void retrieveOrdersHappyFlow() {
        //arrange
        List<ShopOrder> orderList = new ArrayList<>();
        ShopOrder shopOrder = new ShopOrder();
        orderList.add(shopOrder);
        when(shopOrderRepository.findAll()).thenReturn(orderList);
        //act
        List<ShopOrder> orderListResult = shopOrderService.retrieveOrders();
        //assert
        assertNotNull(orderListResult);
        assertEquals(1,orderListResult.size());
        assertEquals(orderList, orderListResult);
    }

    @Test
    @DisplayName("Add brewed coffee to order in a happy flow")
    void addBrewedCoffeeHappyFlow() {
        //arrange
        ShopOrder order = new ShopOrder();
        BrewedCoffee coffee = new BrewedCoffee();
        List<BrewedCoffee> coffeeList = new ArrayList<>();
        coffeeList.add(coffee);
        order.setCoffeeList(coffeeList);
        when(shopOrderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
        when(brewedCoffeeRepository.findById(coffee.getBrewedCoffeeId())).thenReturn(Optional.of(coffee));
        when(shopOrderRepository.save(order)).thenReturn(order);
        //act
        ShopOrder result = shopOrderService.addBrewedCoffee(order.getOrderId(), coffee.getBrewedCoffeeId());
        //assert
        assertNotNull(result);
        assertEquals(order.getOrderId(), result.getOrderId());
        assertEquals(order.getCoffeeList().get(0), result.getCoffeeList().get(0));
    }

    @Test
    @DisplayName("Retrieving order by id in a happy flow")
    void retrieveOrderByIdHappyFlow() {
        //arrange
        ShopOrder order = new ShopOrder();
        when(shopOrderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
        //act
        ShopOrder result = shopOrderService.retrieveOrderById(order.getOrderId());
        //assert
        assertNotNull(result);
        assertEquals(order.getOrderId(), result.getOrderId());
        assertEquals(order.getCoffeeList(), result.getCoffeeList());
        assertEquals(order.getTotalPrice(), result.getTotalPrice());
    }

    @Test
    @DisplayName("Adding discount in a happy flow")
    void addDiscountHappyFlow() {
        //arrange
        ShopOrder order = new ShopOrder();
        BrewedCoffee coffee1 = new BrewedCoffee();
        coffee1.setFinalPrice(3);
        BrewedCoffee coffee2 = new BrewedCoffee();
        coffee1.setFinalPrice(3);
        BrewedCoffee coffee3 = new BrewedCoffee();
        coffee1.setFinalPrice(3);
        List<BrewedCoffee> coffeeList = new ArrayList<>();
        coffeeList.add(coffee1);
        coffeeList.add(coffee2);
        coffeeList.add(coffee3);
        order.setCoffeeList(coffeeList);
        order.setTotalPrice(order.getTotalPrice() - 0.2 * order.getTotalPrice());
        when(shopOrderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
        when(shopOrderRepository.save(order)).thenReturn(order);
        //act
        ShopOrder result = shopOrderService.addDiscount(order.getOrderId());
        //assert
        assertNotNull(result);
        assertEquals(order.getTotalPrice(), result.getTotalPrice());
    }

    @Test
    @DisplayName("Not adding discount - not eligible for discount")
    void addDiscountNegativeFlow() {
        //arrange
        ShopOrder order = new ShopOrder();
        when(shopOrderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
        //act
        OrderNotEligibleForDiscountException exception = assertThrows(OrderNotEligibleForDiscountException.class,
                () -> shopOrderService.addDiscount(order.getOrderId()));
        //assert
        assertNotNull(exception);
        assertEquals("The order must have at least 3 coffees!", exception.getMessage());

        verify(shopOrderRepository, times(0)).save(order);
    }

    @Test
    @DisplayName("Retrieving all discounted orders in a happy flow")
    void retrieveDiscountedOrdersHappyFlow() {
        //arrange
        ShopOrder order = new ShopOrder();
        BrewedCoffee coffee1 = new BrewedCoffee();
        coffee1.setFinalPrice(3);
        BrewedCoffee coffee2 = new BrewedCoffee();
        coffee1.setFinalPrice(3);
        BrewedCoffee coffee3 = new BrewedCoffee();
        coffee1.setFinalPrice(3);
        List<BrewedCoffee> coffeeList = new ArrayList<>();
        coffeeList.add(coffee1);
        coffeeList.add(coffee2);
        coffeeList.add(coffee3);
        order.setCoffeeList(coffeeList);
        order.setTotalPrice(order.getTotalPrice() - 0.2 * order.getTotalPrice());

        List<ShopOrder> orderList = new ArrayList<>();
        orderList.add(order);
        when(shopOrderRepository.findAll()).thenReturn(orderList);
        //act
        List<ShopOrder> orderListResult = shopOrderService.retrieveDiscountedOrders();
        //assert
        assertNotNull(orderListResult);
        assertEquals(orderList, orderListResult);
        assertEquals(orderList.get(0).getTotalPrice(), orderListResult.get(0).getTotalPrice());
    }
}
