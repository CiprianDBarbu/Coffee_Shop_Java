package com.example.coffees.service;

import com.example.coffees.exceptions.NoElementFoundException;
import com.example.coffees.exceptions.OrderNotEligibleForDiscountException;
import com.example.coffees.model.BrewedCoffee;
import com.example.coffees.model.Client;
import com.example.coffees.model.ShopOrder;
import com.example.coffees.repository.BrewedCoffeeRepository;
import com.example.coffees.repository.ClientRepository;
import com.example.coffees.repository.ShopOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopOrderService {
    private final ShopOrderRepository shopOrderRepository;
    private final BrewedCoffeeRepository brewedCoffeeRepository;
    private final ClientRepository clientRepository;

    public ShopOrderService(ShopOrderRepository shopOrderRepository, BrewedCoffeeRepository brewedCoffeeRepository, ClientRepository clientRepository) {
        this.shopOrderRepository = shopOrderRepository;
        this.brewedCoffeeRepository = brewedCoffeeRepository;
        this.clientRepository = clientRepository;
    }

    public List<ShopOrder> retrieveOrders() {
        return shopOrderRepository.findAll();
    }

    public ShopOrder saveNewOrder(ShopOrder shopOrder, int clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NoElementFoundException("Id not available!"));
        shopOrder.setClient(client);

        return shopOrderRepository.save(shopOrder);
    }

    public ShopOrder addBrewedCoffee(int orderId, int brewedCoffeeId) {
        ShopOrder order = shopOrderRepository.findById(orderId)
                .orElseThrow(() -> new NoElementFoundException("Id not available!"));

        BrewedCoffee coffee = brewedCoffeeRepository.findById(brewedCoffeeId)
                .orElseThrow(() -> new NoElementFoundException("Id not available"));

        order.addToCoffeeList(coffee);

        return shopOrderRepository.save(order);
    }

    public ShopOrder retrieveOrderById(int orderId) {
        return shopOrderRepository.findById(orderId)
                .orElseThrow(() -> new NoElementFoundException("Id not available!"));
    }

    public ShopOrder addDiscount(int orderId) {
        ShopOrder order = shopOrderRepository.findById(orderId)
                .orElseThrow(() -> new NoElementFoundException("Id not available!"));

        if(checkIfEligibleForDiscount(order)) {
            double orderPrice = order.getTotalPrice() - 0.2 * order.getTotalPrice();
            order.setTotalPrice(orderPrice);
        } else {
            throw new OrderNotEligibleForDiscountException("The order must have at least 3 coffees!");
        }

        return shopOrderRepository.save(order);
    }

    private boolean checkIfEligibleForDiscount(ShopOrder order) {
        return order.getCoffeeList().size() >= 3;
    }

    public List<ShopOrder> retrieveDiscountedOrders() {
        return shopOrderRepository.findAll().stream()
                .filter(this::checkIfEligibleForDiscount)
                .collect(Collectors.toList());
    }
}
