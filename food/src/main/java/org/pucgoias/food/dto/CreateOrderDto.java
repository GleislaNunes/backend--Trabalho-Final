package org.pucgoias.food.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.pucgoias.food.model.PaymentType;

import java.util.List;

public record CreateOrderDto(
    @NotNull
    Long restaurantId,

    @NotNull
    PaymentType paymentType,

    @NotBlank
    String addressLine1,

    @NotBlank
    String addressLine2,

    @NotBlank
    String city,

    @NotBlank
    String state,

    @NotBlank
    String country,

    @NotBlank
    String postalCode,

    @NotNull
    List<CreateOrderItemDto> items
) {}