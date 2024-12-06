package org.pucgoias.food.controller;

import java.util.List;

import org.pucgoias.food.model.Cart;
import org.pucgoias.food.service.CartService;
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
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * Retrieve all carts.
     *
     * @return List of Cart entities
     */
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.findAll();
        return ResponseEntity.ok(carts);
    }

    /**
     * Retrieve a specific cart by its ID.
     *
     * @param id the ID of the cart
     * @return the Cart entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        Cart cart = cartService.findById(id);
        return ResponseEntity.ok(cart);
    }

    /**
     * Create a new cart.
     *
     * @param cart the Cart entity to create
     * @return the created Cart entity
     */
    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        Cart createdCart = cartService.save(cart);
        return ResponseEntity.ok(createdCart);
    }

    /**
     * Update an existing cart by its ID.
     *
     * @param id   the ID of the cart to update
     * @param cart the updated Cart entity
     * @return the updated Cart entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cart) {
        Cart updatedCart = cartService.update(id, cart);
        return ResponseEntity.ok(updatedCart);
    }

    /**
     * Delete a cart by its ID.
     *
     * @param id the ID of the cart to delete
     * @return a response indicating the deletion was successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
