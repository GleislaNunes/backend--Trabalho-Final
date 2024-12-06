package org.pucgoias.food.service;

import java.util.List;

import org.pucgoias.food.dao.CartRepository;
import org.pucgoias.food.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    /**
     * Retrieve all carts.
     *
     * @return List of Cart entities
     */
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    /**
     * Retrieve a cart by its ID.
     *
     * @param id the ID of the cart
     * @return the Cart entity
     * @throws RuntimeException if the cart is not found
     */
    public Cart findById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + id));
    }

    /**
     * Create a new cart and save it to the database.
     *
     * @param cart the Cart entity to save
     * @return the saved Cart entity
     */
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    /**
     * Update an existing cart by its ID.
     *
     * @param id       the ID of the cart to update
     * @param cartDetails the updated cart data
     * @return the updated Cart entity
     * @throws RuntimeException if the cart is not found
     */
    public Cart update(Long id, Cart cartDetails) {
        Cart cart = findById(id);
        cart.setRestaurant(cartDetails.getRestaurant());
        cart.setCustomerAddress(cartDetails.getCustomerAddress());
        cart.setPaymentType(cartDetails.getPaymentType());
        return cartRepository.save(cart);
    }

    /**
     * Delete a cart by its ID.
     *
     * @param id the ID of the cart to delete
     */
    public void deleteById(Long id) {
        if (!cartRepository.existsById(id)) {
            throw new RuntimeException("Cart not found with id: " + id);
        }
        cartRepository.deleteById(id);
    }
}
