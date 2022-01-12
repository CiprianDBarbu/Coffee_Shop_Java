package com.example.coffees.controller;

import com.example.coffees.model.ShopOrder;
import com.example.coffees.service.ShopOrderService;
import org.hibernate.criterion.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class ShopOrderController {

    private final ShopOrderService shopOrderService;

    public ShopOrderController(ShopOrderService shopOrderService) {
        this.shopOrderService = shopOrderService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ShopOrder>> retrieveOrders() {
        return ResponseEntity.ok()
                .body(shopOrderService.retrieveOrders());
    }

    @GetMapping("/list/{orderId}")
    public ResponseEntity<ShopOrder> retrieveOrderById(@PathVariable int orderId) {
        return ResponseEntity.ok()
                .body(shopOrderService.retrieveOrderById(orderId));
    }

    @PostMapping("/new")
    public ResponseEntity<ShopOrder> saveNewOrder(@RequestBody ShopOrder shopOrder,
                                              @RequestParam int clientId
                                              ) {
        return ResponseEntity.ok()
                .body(shopOrderService.saveNewOrder(shopOrder, clientId));
    }

    @PostMapping("/{orderId}/addBrewedCoffee")
    public ResponseEntity<ShopOrder> addBrewedCoffee(@PathVariable int orderId,
                                                     @RequestParam int brewedCoffeeId) {
        return ResponseEntity.ok()
                .body(shopOrderService.addBrewedCoffee(orderId, brewedCoffeeId));
    }

    @PostMapping("/{orderId}/addDiscount")
    public ResponseEntity<ShopOrder> addDiscount(@PathVariable int orderId) {
        return ResponseEntity.ok()
                .body(shopOrderService.addDiscount(orderId));
    }

    @GetMapping("/discounts")
    public ResponseEntity<List<ShopOrder>> retrieveDiscountedOrders() {
        return ResponseEntity.ok()
                .body(shopOrderService.retrieveDiscountedOrders());
    }
}
