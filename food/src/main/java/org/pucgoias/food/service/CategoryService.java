package org.pucgoias.food.service;

import java.util.List;

import org.pucgoias.food.dao.CategoryRepository;
import org.pucgoias.food.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Retrieve all categories.
     *
     * @return List of Category entities
     */
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * Retrieve a category by its ID.
     *
     * @param id the ID of the category
     * @return the Category entity
     * @throws RuntimeException if the category is not found
     */
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    /**
     * Create a new category and save it to the database.
     *
     * @param category the Category entity to save
     * @return the saved Category entity
     */
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Update an existing category by its ID.
     *
     * @param id           the ID of the category to update
     * @param categoryDetails the updated category data
     * @return the updated Category entity
     * @throws RuntimeException if the category is not found
     */
    public Category update(Long id, Category categoryDetails) {
        Category category = findById(id);
        category.setName(categoryDetails.getName());
        category.setRestaurant(categoryDetails.getRestaurant());
        return categoryRepository.save(category);
    }

    /**
     * Delete a category by its ID.
     *
     * @param id the ID of the category to delete
     */
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}