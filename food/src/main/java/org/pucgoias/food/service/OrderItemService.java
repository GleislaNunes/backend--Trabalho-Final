package org.pucgoias.food.service;
import java.util.List;

import org.pucgoias.food.dao.OrderItemRepository;
import org.pucgoias.food.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    /**
     * Retrieve all order items.
     *
     * @return List of OrderItem entities
     */
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    /**
     * Retrieve an order item by its ID.
     *
     * @param id the ID of the order item
     * @return the OrderItem entity
     * @throws RuntimeException if the order item is not found
     */
    public OrderItem findById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found with id: " + id));
    }

    /**
     * Create a new order item and save it to the database.
     *
     * @param orderItem the OrderItem entity to save
     * @return the saved OrderItem entity
     */
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    /**
     * Update an existing order item by its ID.
     *
     * @param id              the ID of the order item to update
     * @param orderItemDetails the updated order item data
     * @return the updated OrderItem entity
     * @throws RuntimeException if the order item is not found
     */
    public OrderItem update(Long id, OrderItem orderItemDetails) {
        OrderItem orderItem = findById(id);
        orderItem.setOrder(orderItemDetails.getOrder());
        orderItem.setProduct(orderItemDetails.getProduct());
        orderItem.setQuantity(orderItemDetails.getQuantity());
        orderItem.setNotes(orderItemDetails.getNotes());
        orderItem.setPrice(orderItemDetails.getPrice());
        return orderItemRepository.save(orderItem);
    }

    /**
     * Delete an order item by its ID.
     *
     * @param id the ID of the order item to delete
     */
    public void deleteById(Long id) {
        if (!orderItemRepository.existsById(id)) {
            throw new RuntimeException("OrderItem not found with id: " + id);
        }
        orderItemRepository.deleteById(id);
    }
}
