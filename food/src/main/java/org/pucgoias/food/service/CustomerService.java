package org.pucgoias.food.service;

import java.util.List;

import org.pucgoias.food.dao.CustomerRepository;
import org.pucgoias.food.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Retrieve all customers.
     *
     * @return List of Customer entities
     */
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    /**
     * Retrieve a customer by its ID.
     *
     * @param id the ID of the customer
     * @return the Customer entity
     * @throws RuntimeException if the customer is not found
     */
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    /**
     * Create a new customer and save it to the database.
     *
     * @param customer the Customer entity to save
     * @return the saved Customer entity
     */
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Update an existing customer by its ID.
     *
     * @param id            the ID of the customer to update
     * @param customerDetails the updated customer data
     * @return the updated Customer entity
     * @throws RuntimeException if the customer is not found
     */
    public Customer update(Long id, Customer customerDetails) {
        Customer customer = findById(id);
        customer.setName(customerDetails.getName());
        customer.setCpf(customerDetails.getCpf());
        customer.setPhoneNumber(customerDetails.getPhoneNumber());
        customer.setUser(customerDetails.getUser());
        return customerRepository.save(customer);
    }

    /**
     * Delete a customer by its ID.
     *
     * @param id the ID of the customer to delete
     */
    public void deleteById(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }
}
