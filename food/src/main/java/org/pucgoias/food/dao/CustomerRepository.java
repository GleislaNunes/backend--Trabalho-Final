package org.pucgoias.food.dao;

import org.pucgoias.food.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByPhoneNumber(String phoneNumber);
    Customer findByUserId(Long userId);
}
