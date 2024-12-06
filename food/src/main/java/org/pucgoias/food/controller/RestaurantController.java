package org.pucgoias.food.controller;

import java.util.List;

import org.pucgoias.food.model.Restaurant;
import org.pucgoias.food.service.RestaurantService;
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
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    /**
     * Retrieve all restaurants.
     *
     * @return List of Restaurant entities
     */
    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.findAll();
        return ResponseEntity.ok(restaurants);
    }

    /**
     * Retrieve a specific restaurant by its ID.
     *
     * @param id the ID of the restaurant
     * @return the Restaurant entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.findById(id);
        return ResponseEntity.ok(restaurant);
    }

    /**
     * Create a new restaurant.
     *
     * @param restaurant the Restaurant entity to create
     * @return the created Restaurant entity
     */
    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant createdRestaurant = restaurantService.save(restaurant);
        return ResponseEntity.ok(createdRestaurant);
    }

    /**
     * Update an existing restaurant by its ID.
     *
     * @param id         the ID of the restaurant to update
     * @param restaurant the updated Restaurant entity
     * @return the updated Restaurant entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        Restaurant updatedRestaurant = restaurantService.update(id, restaurant);
        return ResponseEntity.ok(updatedRestaurant);
    }

    /**
     * Delete a restaurant by its ID.
     *
     * @param id the ID of the restaurant to delete
     * @return a response indicating the deletion was successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
