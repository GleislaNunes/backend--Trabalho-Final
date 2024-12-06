package org.pucgoias.food.service;

import java.util.List;

import org.pucgoias.food.dao.UserRepository;
import org.pucgoias.food.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieve all users.
     *
     * @return List of User entities
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Retrieve a user by its ID.
     *
     * @param id the ID of the user
     * @return the User entity
     * @throws RuntimeException if the user is not found
     */
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    /**
     * Create a new user and save it to the database.
     *
     * @param user the User entity to save
     * @return the saved User entity
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Update an existing user by its ID.
     *
     * @param id        the ID of the user to update
     * @param userDetails the updated user data
     * @return the updated User entity
     * @throws RuntimeException if the user is not found
     */
    public User update(Long id, User userDetails) {
        User user = findById(id);
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setType(userDetails.getType());
        return userRepository.save(user);
    }

    /**
     * Delete a user by its ID.
     *
     * @param id the ID of the user to delete
     */
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
