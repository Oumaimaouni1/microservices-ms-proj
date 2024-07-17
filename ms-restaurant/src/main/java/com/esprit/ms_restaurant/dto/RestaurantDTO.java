package com.esprit.ms_restaurant.dto;

import com.esprit.ms_restaurant.mapper.RestaurantMapper;
import com.esprit.ms_restaurant.model.Restaurant;
import lombok.Builder;

@Builder
public record RestaurantDTO(String restauId, String name, String localisation) {

    // Static method to map from Stock entity to StockDTO
    public static RestaurantDTO mapFromEntity(Restaurant restaurant) {
        return RestaurantMapper.INSTANCE.toDto(restaurant);
    }

    // Static method to map from StockDTO to Stock entity
    public static Restaurant mapToEntity(RestaurantDTO restaurantDTO) {
        return RestaurantMapper.INSTANCE.toEntity(restaurantDTO);
    }

}
