package org.pucgoias.food.controller;

import java.util.List;

import org.pucgoias.food.model.Customer;
import org.pucgoias.food.service.CustomerService;
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
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Retrieve all customers.
     *
     * @return List of Customer entities
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    /**
     * Retrieve a specific customer by its ID.
     *
     * @param id the ID of the customer
     * @return the Customer entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        return ResponseEntity.ok(customer);
    }

    /**
     * Create a new customer.
     *
     * @param customer the Customer entity to create
     * @return the created Customer entity
     */
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.save(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    /**
     * Update an existing customer by its ID.
     *
     * @param id       the ID of the customer to update
     * @param customer the updated Customer entity
     * @return the updated Customer entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.update(id, customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    /**
     * Delete a customer by its ID.
     *
     * @param id the ID of the customer to delete
     * @return a response indicating the deletion was successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
