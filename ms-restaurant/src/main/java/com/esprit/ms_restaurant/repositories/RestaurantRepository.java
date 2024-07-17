package com.esprit.ms_restaurant.repositories;

import com.esprit.ms_restaurant.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
}
