package com.example.coffees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BrewedCoffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brewedCoffeeId;
    @Column(name = "brewed_coffee_name")
    @NotEmpty(message = "Brewed coffee name cannot be empty!")
    @Length(min = 2, message = "The name must have at least 2 characters!")
    private String name;
    @Column(name = "final_price")
    @Min(value = 0, message = "The price has to be above a positive value!")
    private double finalPrice = 0;
    @Column(name = "has_milk")
    private boolean hasMilk = false;

    @OneToOne
    @JoinColumn(name = "coffee_id")
    private Coffee coffee;

    @OneToOne
    @JoinColumn(name = "syrup_id")
    private Syrup syrup;

    @ManyToMany(mappedBy = "coffeeList")
    @JsonIgnore
    private List<ShopOrder> orderList = new ArrayList<>();

    public BrewedCoffee() {
    }

    public BrewedCoffee(String name, boolean hasMilk, Coffee coffee, Syrup syrup) {
        this.name = name;
        this.hasMilk = hasMilk;
        this.coffee = coffee;
        this.syrup = syrup;
        this.finalPrice = coffee.getPrice() + syrup.getPrice();
        if(hasMilk) {finalPrice += 0.15;}
    }

    public BrewedCoffee(String name, boolean hasMilk) {
        this.name = name;
        this.hasMilk = hasMilk;
    }

    public int getBrewedCoffeeId() {
        return brewedCoffeeId;
    }

    public void setBrewedCoffeeId(int brewedCoffeeId) {
        this.brewedCoffeeId = brewedCoffeeId;
    }

    public List<ShopOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<ShopOrder> orderList) {
        this.orderList = orderList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public boolean isHasMilk() {
        return hasMilk;
    }

    public void setHasMilk(boolean hasMilk) {
        if((this.hasMilk) && (!hasMilk)) {this.finalPrice -= 0.15;}
        this.hasMilk = hasMilk;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
        this.finalPrice += this.coffee.getPrice();
    }

    public Syrup getSyrup() {
        return syrup;
    }

    public void setSyrup(Syrup syrup) {
        this.syrup = syrup;
        this.finalPrice += this.syrup.getPrice();
    }
}
