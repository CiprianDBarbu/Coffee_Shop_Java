package com.example.coffees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;
    @Column(name = "client_name")
    @NotEmpty(message = "Client name cannot be empty!")
    @Length(min = 2, message = "The name must have at least 2 characters!")
    private String name;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<ShopOrder> orderList = new ArrayList<>();

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShopOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<ShopOrder> orderList) {
        this.orderList = orderList;
    }
}
