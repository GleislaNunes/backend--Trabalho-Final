package org.pucgoias.food.service;

import java.util.List;

import org.pucgoias.food.dao.CartItemRepository;
import org.pucgoias.food.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    /**
     * Retrieve all cart items from the database.
     *
     * @return List of CartItem entities
     */
    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    /**
     * Retrieve a cart item by its ID.
     *
     * @param id the ID of the cart item
     * @return the CartItem entity
     * @throws RuntimeException if the cart item is not found
     */
    public CartItem findById(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem not found with id: " + id));
    }

    /**
     * Create a new cart item and save it to the database.
     *
     * @param cartItem the CartItem entity to save
     * @return the saved CartItem entity
     */
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    /**
     * Update an existing cart item by its ID.
     *
     * @param id             the ID of the cart item to update
     * @param cartItemDetails the updated cart item data
     * @return the updated CartItem entity
     * @throws RuntimeException if the cart item is not found
     */
    public CartItem update(Long id, CartItem cartItemDetails) {
        CartItem cartItem = findById(id);
        cartItem.setCart(cartItemDetails.getCart());
        cartItem.setProduct(cartItemDetails.getProduct());
        cartItem.setQuantity(cartItemDetails.getQuantity());
        cartItem.setNotes(cartItemDetails.getNotes());
        return cartItemRepository.save(cartItem);
    }

    /**
     * Delete a cart item by its ID.
     *
     * @param id the ID of the cart item to delete
     */
    public void deleteById(Long id) {
        if (!cartItemRepository.existsById(id)) {
            throw new RuntimeException("CartItem not found with id: " + id);
        }
        cartItemRepository.deleteById(id);
    }
}