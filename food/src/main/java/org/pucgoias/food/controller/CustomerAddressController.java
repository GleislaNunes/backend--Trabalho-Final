package org.pucgoias.food.controller;

import java.util.List;

import org.pucgoias.food.model.CustomerAddress;
import org.pucgoias.food.service.CustomerAddressService;
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
@RequestMapping("/api/customer-addresses")
public class CustomerAddressController {

    @Autowired
    private CustomerAddressService customerAddressService;

    /**
     * Retrieve all customer addresses.
     *
     * @return List of CustomerAddress entities
     */
    @GetMapping
    public ResponseEntity<List<CustomerAddress>> getAllCustomerAddresses() {
        List<CustomerAddress> customerAddresses = customerAddressService.findAll();
        return ResponseEntity.ok(customerAddresses);
    }

    /**
     * Retrieve a specific customer address by its ID.
     *
     * @param id the ID of the customer address
     * @return the CustomerAddress entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerAddress> getCustomerAddressById(@PathVariable Long id) {
        CustomerAddress customerAddress = customerAddressService.findById(id);
        return ResponseEntity.ok(customerAddress);
    }

    /**
     * Create a new customer address.
     *
     * @param customerAddress the CustomerAddress entity to create
     * @return the created CustomerAddress entity
     */
    @PostMapping
    public ResponseEntity<CustomerAddress> createCustomerAddress(@RequestBody CustomerAddress customerAddress) {
        CustomerAddress createdCustomerAddress = customerAddressService.save(customerAddress);
        return ResponseEntity.ok(createdCustomerAddress);
    }

    /**
     * Update an existing customer address by its ID.
     *
     * @param id             the ID of the customer address to update
     * @param customerAddress the updated CustomerAddress entity
     * @return the updated CustomerAddress entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomerAddress> updateCustomerAddress(@PathVariable Long id, @RequestBody CustomerAddress customerAddress) {
        CustomerAddress updatedCustomerAddress = customerAddressService.update(id, customerAddress);
        return ResponseEntity.ok(updatedCustomerAddress);
    }

    /**
     * Delete a customer address by its ID.
     *
     * @param id the ID of the customer address to delete
     * @return a response indicating the deletion was successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerAddress(@PathVariable Long id) {
        customerAddressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

