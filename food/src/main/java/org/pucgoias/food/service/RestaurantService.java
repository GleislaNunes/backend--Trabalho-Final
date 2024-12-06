package org.pucgoias.food.service;

import java.util.List;

import org.pucgoias.food.dao.RestaurantRepository;
import org.pucgoias.food.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    /**
     * Retrieve all restaurants.
     *
     * @return List of Restaurant entities
     */
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    /**
     * Retrieve a restaurant by its ID.
     *
     * @param id the ID of the restaurant
     * @return the Restaurant entity
     * @throws RuntimeException if the restaurant is not found
     */
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
    }

    /**
     * Create a new restaurant and save it to the database.
     *
     * @param restaurant the Restaurant entity to save
     * @return the saved Restaurant entity
     */
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    /**
     * Update an existing restaurant by its ID.
     *
     * @param id                the ID of the restaurant to update
     * @param restaurantDetails the updated restaurant data
     * @return the updated Restaurant entity
     * @throws RuntimeException if the restaurant is not found
     */
    public Restaurant update(Long id, Restaurant restaurantDetails) {
        Restaurant restaurant = findById(id);
        restaurant.setUser(restaurantDetails.getUser());
        restaurant.setName(restaurantDetails.getName());
        restaurant.setImage(restaurantDetails.getImage());
        restaurant.setPostalCode(restaurantDetails.getPostalCode());
        restaurant.setAddressLine1(restaurantDetails.getAddressLine1());
        restaurant.setAddressLine2(restaurantDetails.getAddressLine2());
        restaurant.setCity(restaurantDetails.getCity());
        restaurant.setState(restaurantDetails.getState());
        restaurant.setCountry(restaurantDetails.getCountry());
        restaurant.setDeliveryPrice(restaurantDetails.getDeliveryPrice());
        restaurant.setDeliveryRadius(restaurantDetails.getDeliveryRadius());
        restaurant.setLatitude(restaurantDetails.getLatitude());
        restaurant.setLongitude(restaurantDetails.getLongitude());
        restaurant.setPhoneNumber(restaurantDetails.getPhoneNumber());
        return restaurantRepository.save(restaurant);
    }

    /**
     * Delete a restaurant by its ID.
     *
     * @param id the ID of the restaurant to delete
     */
    public void deleteById(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new RuntimeException("Restaurant not found with id: " + id);
        }
        restaurantRepository.deleteById(id);
    }
}
