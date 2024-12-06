package org.pucgoias.food.service;

import java.util.List;

import org.pucgoias.food.dao.OrderRepository;
import org.pucgoias.food.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Retrieve all orders.
     *
     * @return List of Order entities
     */
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    /**
     * Retrieve an order by its ID.
     *
     * @param id the ID of the order
     * @return the Order entity
     * @throws RuntimeException if the order is not found
     */
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    /**
     * Create a new order and save it to the database.
     *
     * @param order the Order entity to save
     * @return the saved Order entity
     */
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Update an existing order by its ID.
     *
     * @param id         the ID of the order to update
     * @param orderDetails the updated order data
     * @return the updated Order entity
     * @throws RuntimeException if the order is not found
     */
    public Order update(Long id, Order orderDetails) {
        Order order = findById(id);
        order.setRestaurant(orderDetails.getRestaurant());
        order.setCustomer(orderDetails.getCustomer());
        order.setPostalCode(orderDetails.getPostalCode());
        order.setAddressLine1(orderDetails.getAddressLine1());
        order.setAddressLine2(orderDetails.getAddressLine2());
        order.setCity(orderDetails.getCity());
        order.setState(orderDetails.getState());
        order.setCountry(orderDetails.getCountry());
        order.setLatitude(orderDetails.getLatitude());
        order.setLongitude(orderDetails.getLongitude());
        order.setPaymentType(orderDetails.getPaymentType());
        order.setTotalPrice(orderDetails.getTotalPrice());
        order.setStatus(orderDetails.getStatus());
        return orderRepository.save(order);
    }

    /**
     * Delete an order by its ID.
     *
     * @param id the ID of the order to delete
     */
    public void deleteById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }
}
