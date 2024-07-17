package com.esprit.ms_restaurant.mapper;

import com.esprit.ms_restaurant.dto.RestaurantDTO;
import com.esprit.ms_restaurant.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);
    @Mapping(target = "restauId", source = "id")
    RestaurantDTO toDto(Restaurant stock);
    @Mapping(target = "id", source = "restauId")
    Restaurant toEntity(RestaurantDTO restaurantDTO);
}
