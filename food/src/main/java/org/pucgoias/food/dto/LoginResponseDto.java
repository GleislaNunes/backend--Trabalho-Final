package org.pucgoias.food.dto;

import org.pucgoias.food.model.Customer;
import org.pucgoias.food.model.UserType;

public record LoginResponseDto(
    Long id,
    String email,
    String token,
    Customer customer
) {
}
