package org.pucgoias.food.controller;

import java.util.List;

import org.pucgoias.food.model.Category;
import org.pucgoias.food.service.CategoryService;
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
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Retrieve all categories.
     *
     * @return List of Category entities
     */
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    /**
     * Retrieve a specific category by its ID.
     *
     * @param id the ID of the category
     * @return the Category entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    /**
     * Create a new category.
     *
     * @param category the Category entity to create
     * @return the created Category entity
     */
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.save(category);
        return ResponseEntity.ok(createdCategory);
    }

    /**
     * Update an existing category by its ID.
     *
     * @param id       the ID of the category to update
     * @param category the updated Category entity
     * @return the updated Category entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.update(id, category);
        return ResponseEntity.ok(updatedCategory);
    }

    /**
     * Delete a category by its ID.
     *
     * @param id the ID of the category to delete
     * @return a response indicating the deletion was successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}