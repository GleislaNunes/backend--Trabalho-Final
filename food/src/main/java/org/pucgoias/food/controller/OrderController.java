package org.pucgoias.food.controller;

import jakarta.validation.Valid;
import org.pucgoias.food.dto.CreateOrderDto;
import org.pucgoias.food.model.Order;
import org.pucgoias.food.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody CreateOrderDto order) {
        Order createdOrder = orderService.save(order);
        return ResponseEntity.ok(createdOrder);
    }
}
