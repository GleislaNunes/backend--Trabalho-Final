package org.pucgoias.food.dto;

import jakarta.validation.constraints.NotNull;

public record CreateOrderItemDto(
    @NotNull
    Long productId,

    @NotNull
    int quantity,

    String notes
) {}
