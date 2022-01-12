package com.example.coffees.exceptions;

public class OrderNotEligibleForDiscountException extends RuntimeException {
    public OrderNotEligibleForDiscountException(String message) {
        super(message);
    }
}
