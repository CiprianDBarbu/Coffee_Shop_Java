package com.example.coffees.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShopOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @Column(name = "total_price")
    @Min(value = 0, message = "The price has to be above a positive value!")
    private double totalPrice = 0;

    @ManyToMany
    @JoinTable(name = "order_coffee", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "brewed_coffee_id"))
    private List<BrewedCoffee> coffeeList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public ShopOrder() {
    }

    public ShopOrder(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<BrewedCoffee> getCoffeeList() {
        return coffeeList;
    }

    public void setCoffeeList(List<BrewedCoffee> coffeeList) {
        this.coffeeList = coffeeList;
        recalculateTotalPrice();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    private void recalculateTotalPrice() {
        this.totalPrice = 0;
        for (BrewedCoffee brewedCoffee :
                this.coffeeList) {
            this.totalPrice += brewedCoffee.getFinalPrice();
        }
    }

    public void addToCoffeeList(BrewedCoffee coffee) {
        this.coffeeList.add(coffee);
//        Work with recalculate but it is not optimal
//        recalculateTotalPrice();
        this.totalPrice += coffee.getFinalPrice();
    }
}
