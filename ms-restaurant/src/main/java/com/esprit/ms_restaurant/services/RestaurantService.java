package com.esprit.ms_restaurant.services;


import com.esprit.ms_restaurant.dto.RestaurantDTO;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDTO> findAllRestaurants();
    RestaurantDTO getRestaurantById(String restaurantId);
    RestaurantDTO saveRestaurant(RestaurantDTO restaurantDTO);
    void deleteRestaurantById(String restaurantId);
    void sendRestaurant(String restaurantId);
}

