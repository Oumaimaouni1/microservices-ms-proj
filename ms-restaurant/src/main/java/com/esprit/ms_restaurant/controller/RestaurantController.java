package com.esprit.ms_restaurant.controller;

import com.esprit.ms_restaurant.dto.RestaurantDTO;
import com.esprit.ms_restaurant.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@Slf4j
@RequiredArgsConstructor
@RefreshScope
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.findAllRestaurants();
    }

    @GetMapping("/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable String id) {
        log.info("this Restaurant is solicited now");
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping
    public RestaurantDTO createRestaurant(@RequestBody RestaurantDTO RestaurantDTO) {
        return restaurantService.saveRestaurant(RestaurantDTO);
    }

    @PutMapping("/{id}")
    public RestaurantDTO updateRestaurant(@PathVariable String id, @RequestBody RestaurantDTO RestaurantDTO) {return restaurantService.saveRestaurant(RestaurantDTO);}

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable String id) {
        restaurantService.deleteRestaurantById(id);
    }

}
