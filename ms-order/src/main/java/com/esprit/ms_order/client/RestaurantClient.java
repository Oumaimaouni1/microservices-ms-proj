package com.esprit.ms_order.client;

import com.esprit.ms_order.dto.OrderDTO;
import com.esprit.ms_order.dto.RestaurantDTO;
import com.esprit.ms_order.foreign.FeignConfig;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MS-RESTAURANT", configuration = FeignConfig.class)
public interface RestaurantClient {

    @GetMapping("/events/{id}")
    @CircuitBreaker(name="msRestaurant",fallbackMethod ="fallbackGetRestaurantById")
    RestaurantDTO getRestaurantById(@PathVariable("id") String id);

    default RestaurantDTO fallbackGetRestaurantById(String id, Throwable throwable) {
        return new RestaurantDTO("0", "Fallback", "na");
    }

}
