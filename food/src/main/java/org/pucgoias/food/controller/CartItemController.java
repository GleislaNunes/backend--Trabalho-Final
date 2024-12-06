package org.pucgoias.food.controller;

import java.util.List;

import org.pucgoias.food.model.CartItem;
import org.pucgoias.food.service.CartItemService;
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
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    /**
     * Retrieve all cart items.
     *
     * @return List of CartItem entities
     */
    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        List<CartItem> cartItems = cartItemService.findAll();
        return ResponseEntity.ok(cartItems);
    }

    /**
     * Retrieve a specific cart item by its ID.
     *
     * @param id the ID of the cart item
     * @return the CartItem entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Long id) {
        CartItem cartItem = cartItemService.findById(id);
        return ResponseEntity.ok(cartItem);
    }

    /**
     * Create a new cart item.
     *
     * @param cartItem the CartItem entity to create
     * @return the created CartItem entity
     */
    @PostMapping
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem) {
        CartItem createdCartItem = cartItemService.save(cartItem);
        return ResponseEntity.ok(createdCartItem);
    }

    /**
     * Update an existing cart item by its ID.
     *
     * @param id       the ID of the cart item to update
     * @param cartItem the updated CartItem entity
     * @return the updated CartItem entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long id, @RequestBody CartItem cartItem) {
        CartItem updatedCartItem = cartItemService.update(id, cartItem);
        return ResponseEntity.ok(updatedCartItem);
    }

    /**
     * Delete a cart item by its ID.
     *
     * @param id the ID of the cart item to delete
     * @return a response indicating the deletion was successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}