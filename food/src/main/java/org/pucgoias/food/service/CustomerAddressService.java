package org.pucgoias.food.service;

import java.util.List;

import org.pucgoias.food.dao.CustomerAddressRepository;
import org.pucgoias.food.model.CustomerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAddressService {

    @Autowired
    private CustomerAddressRepository customerAddressRepository;

    /**
     * Retrieve all customer addresses.
     *
     * @return List of CustomerAddress entities
     */
    public List<CustomerAddress> findAll() {
        return customerAddressRepository.findAll();
    }

    /**
     * Retrieve a customer address by its ID.
     *
     * @param id the ID of the customer address
     * @return the CustomerAddress entity
     * @throws RuntimeException if the customer address is not found
     */
    public CustomerAddress findById(Long id) {
        return customerAddressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CustomerAddress not found with id: " + id));
    }

    /**
     * Create a new customer address and save it to the database.
     *
     * @param customerAddress the CustomerAddress entity to save
     * @return the saved CustomerAddress entity
     */
    public CustomerAddress save(CustomerAddress customerAddress) {
        return customerAddressRepository.save(customerAddress);
    }

    /**
     * Update an existing customer address by its ID.
     *
     * @param id                 the ID of the customer address to update
     * @param customerAddressDetails the updated customer address data
     * @return the updated CustomerAddress entity
     * @throws RuntimeException if the customer address is not found
     */
    public CustomerAddress update(Long id, CustomerAddress customerAddressDetails) {
        CustomerAddress customerAddress = findById(id);
        customerAddress.setCustomer(customerAddressDetails.getCustomer());
        customerAddress.setPostalCode(customerAddressDetails.getPostalCode());
        customerAddress.setAddressLine1(customerAddressDetails.getAddressLine1());
        customerAddress.setAddressLine2(customerAddressDetails.getAddressLine2());
        customerAddress.setCity(customerAddressDetails.getCity());
        customerAddress.setState(customerAddressDetails.getState());
        customerAddress.setCountry(customerAddressDetails.getCountry());
        customerAddress.setLatitude(customerAddressDetails.getLatitude());
        customerAddress.setLongitude(customerAddressDetails.getLongitude());
        return customerAddressRepository.save(customerAddress);
    }

    /**
     * Delete a customer address by its ID.
     *
     * @param id the ID of the customer address to delete
     */
    public void deleteById(Long id) {
        if (!customerAddressRepository.existsById(id)) {
            throw new RuntimeException("CustomerAddress not found with id: " + id);
        }
        customerAddressRepository.deleteById(id);
    }
}
