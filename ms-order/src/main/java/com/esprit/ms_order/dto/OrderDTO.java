package com.esprit.ms_order.dto;

import lombok.Builder;

@Builder
public record OrderDTO(Long orderId, Float price, String restaurantId, RestaurantDTO restaurantDTO) {


}
