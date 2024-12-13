package org.pucgoias.food.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateCustomerDto(
    @NotBlank
    String name,

    @NotBlank
    String cpf,

    @NotBlank
    String phone,

    @Email
    @NotBlank
    String email,

    @NotBlank
    String password
) {
}
