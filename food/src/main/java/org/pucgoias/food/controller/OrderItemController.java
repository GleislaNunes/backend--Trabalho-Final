package org.pucgoias.food.controller;

import java.util.List;

import org.pucgoias.food.model.OrderItem;
import org.pucgoias.food.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    /**
     * Retrieve all order items.
     *
     * @return List of OrderItem entities
     */
    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.findAll();
        return ResponseEntity.ok(orderItems);
    }

    /**
     * Retrieve a specific order item by its ID.
     *
     * @param id the ID of the order item
     * @return the OrderItem entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        OrderItem orderItem = orderItemService.findById(id);
        return ResponseEntity.ok(orderItem);
    }

    /**
     * Create a new order item.
     *
     * @param orderItem the OrderItem entity to create
     * @return the created OrderItem entity
     */
    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem createdOrderItem = orderItemService.save(orderItem);
        return ResponseEntity.ok(createdOrderItem);
    }

    /**
     * Update an existing order item by its ID.
     *
     * @param id        the ID of the order item to update
     * @param orderItem the updated OrderItem entity
     * @return the updated OrderItem entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        OrderItem updatedOrderItem = orderItemService.update(id, orderItem);
        return ResponseEntity.ok(updatedOrderItem);
    }

    /**
     * Delete an order item by its ID.
     *
     * @param id the ID of the order item to delete
     * @return a response indicating the deletion was successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
