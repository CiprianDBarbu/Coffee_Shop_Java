package com.example.coffees.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
public class Syrup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int syrupId;
    @Column(name = "syrup_name")
    @NotEmpty(message = "Syrup name cannot be empty!")
    @Length(min = 2, message = "The name must have at least 2 characters!")
    private String name;
    @Column(name = "syrup_price")
    @Min(value = 0, message = "The price has to be above a positive value!")
    private double price;

    public Syrup() {
    }

    public Syrup( String name, double price) {
        this.name = name;
        this.price = price;
    }

    public int getSyrupId() {
        return syrupId;
    }

    public void setSyrupId(int syrupId) {
        this.syrupId = syrupId;
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
