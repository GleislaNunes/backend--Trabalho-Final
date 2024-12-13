package org.pucgoias.food.service;

import java.math.BigDecimal;

import jakarta.transaction.Transactional;
import org.pucgoias.food.dao.*;
import org.pucgoias.food.dto.CreateOrderDto;
import org.pucgoias.food.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Order save(CreateOrderDto data) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Restaurant restaurant = restaurantRepository.findById(data.restaurantId())
            .orElseThrow();

        Customer customer = customerRepository.findByUserId(user.getId());

        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setPaymentType(data.paymentType());
        order.setAddressLine1(data.addressLine1());
        order.setAddressLine2(data.addressLine2());
        order.setCity(data.city());
        order.setState(data.state());
        order.setCountry(data.country());
        order.setPostalCode(data.postalCode());
        order.setStatus(OrderStatus.PENDING);
        order = orderRepository.save(order);

        double totalPrice = restaurant.getDeliveryPrice().doubleValue();
        for (var item : data.items()) {
            Product product = productRepository.findById(item.productId())
                .orElseThrow();

            totalPrice += product.getPrice().doubleValue() * item.quantity();

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(item.quantity());
            orderItem.setNotes(item.notes());
            orderItem = orderItemRepository.save(orderItem);
        }

        order.setTotalPrice(new BigDecimal(totalPrice));
        order = orderRepository.save(order);

        return order;
    }
}
