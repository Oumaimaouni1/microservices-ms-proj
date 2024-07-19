package com.esprit.ms_restaurant.impl;

import com.esprit.ms_restaurant.dto.RestaurantDTO;
import com.esprit.ms_restaurant.model.Restaurant;
import com.esprit.ms_restaurant.repositories.RestaurantRepository;
import com.esprit.ms_restaurant.services.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository repository;
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "restaurant-topic";

    public List<RestaurantDTO> findAllRestaurants() {
        return repository.findAll().stream()
                .map(RestaurantDTO::mapFromEntity)
                .collect(Collectors.toList());
    }

    public RestaurantDTO getRestaurantById(String restaurantId) {
        Restaurant restaurant = repository.findById(restaurantId).orElse(null);
        return RestaurantDTO.mapFromEntity(restaurant);
    }

    public RestaurantDTO saveRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = RestaurantDTO.mapToEntity(restaurantDTO);
        restaurant = repository.save(restaurant);
        return RestaurantDTO.mapFromEntity(restaurant);
    }
    public void sendRestaurant(String restaurantId) {
        kafkaTemplate.send(TOPIC, restaurantId);
        log.info("Produced event: {}", restaurantId);
    }
    public void deleteRestaurantById(String restaurantId) {
        repository.deleteById(restaurantId);
     //   sendRestaurant(restaurantId);
    }

}

