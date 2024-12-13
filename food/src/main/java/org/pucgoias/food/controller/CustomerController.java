package org.pucgoias.food.controller;

import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.pucgoias.food.dto.CreateCustomerDto;
import org.pucgoias.food.dto.LoginResponseDto;
import org.pucgoias.food.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<LoginResponseDto> createCustomer(@Valid @RequestBody CreateCustomerDto createCustomerDto) throws BadRequestException {
        return ResponseEntity.ok(customerService.save(createCustomerDto));
    }
}
