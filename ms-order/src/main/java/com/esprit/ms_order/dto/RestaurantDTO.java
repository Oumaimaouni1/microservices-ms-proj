package com.esprit.ms_order.dto;

import lombok.Builder;

@Builder
public record RestaurantDTO(
        String restaurantId,
        String name,
        String localisation) {
}
