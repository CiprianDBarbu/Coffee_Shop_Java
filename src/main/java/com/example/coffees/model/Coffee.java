package com.example.coffees.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coffeeId;
    @Column(name = "coffee_name")
    @NotEmpty(message = "Coffee name cannot be empty!")
    @Length(min = 2, message = "The name must have at least 2 characters!")
    private String name;
    @Column(name = "coffee_price")
    @Min(value = 0, message = "The price has to be above a positive value!")
    private double price;

    public Coffee() {
    }

    public Coffee(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Coffee(int coffeeId, @NotEmpty(message = "Coffee name cannot be empty!") @Length(min = 2, message = "The name must have at least 2 characters!") String name, @Min(value = 0, message = "The price has to be above a positive value!") double price) {
        this.coffeeId = coffeeId;
        this.name = name;
        this.price = price;
    }

    public int getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(int coffeeId) {
        this.coffeeId = coffeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
