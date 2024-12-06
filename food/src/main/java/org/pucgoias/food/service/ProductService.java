package org.pucgoias.food.service;

import java.util.List;

import org.pucgoias.food.dao.ProductRepository;
import org.pucgoias.food.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Retrieve all products.
     *
     * @return List of Product entities
     */
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Retrieve a product by its ID.
     *
     * @param id the ID of the product
     * @return the Product entity
     * @throws RuntimeException if the product is not found
     */
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    /**
     * Create a new product and save it to the database.
     *
     * @param product the Product entity to save
     * @return the saved Product entity
     */
    public Product save(Product product) {
        return productRepository.save(product);
    }

    /**
     * Update an existing product by its ID.
     *
     * @param id            the ID of the product to update
     * @param productDetails the updated product data
     * @return the updated Product entity
     * @throws RuntimeException if the product is not found
     */
    public Product update(Long id, Product productDetails) {
        Product product = findById(id);
        product.setCategory(productDetails.getCategory());
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        return productRepository.save(product);
    }

    /**
     * Delete a product by its ID.
     *
     * @param id the ID of the product to delete
     */
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
