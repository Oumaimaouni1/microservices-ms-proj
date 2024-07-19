package com.esprit.ms_order.client;

import com.esprit.ms_order.dto.RestaurantDTO;
import com.esprit.ms_order.foreign.FeignConfig;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "MS-RESTAURANT", configuration = FeignConfig.class)
public interface RestaurantClient {

    @GetMapping("/restaurants/{id}")
    @CircuitBreaker(name="msRestaurant",fallbackMethod ="fallbackGetRestaurantById")
    RestaurantDTO getRestaurantById(@PathVariable("id") String id);

}
