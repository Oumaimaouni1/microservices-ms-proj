package com.esprit.ms_order.impl;

import com.esprit.ms_order.client.RestaurantClient;
import com.esprit.ms_order.dto.OrderDTO;
import com.esprit.ms_order.dto.RestaurantDTO;
import com.esprit.ms_order.mapper.OrderMappper;
import com.esprit.ms_order.model.Order;
import com.esprit.ms_order.repositories.OrderRepository;
import com.esprit.ms_order.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private RestaurantClient restaurantClient;
    private OrderMappper orderMappper;
    private RestTemplate restTemplate;
    private static final String RESTAURANT_SERVICE_URL = "http://MS-RESTAURANT/restaurants/";

    public OrderDTO getOrderById(Long id) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = jwt.getTokenValue();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return orderRepository.findById(id).map(order -> {
            ResponseEntity<RestaurantDTO> response = restTemplate.exchange(
                    RESTAURANT_SERVICE_URL + order.getOrderId(),
                    HttpMethod.GET,
                    entity,
                    RestaurantDTO.class
            );
            RestaurantDTO restaurantDTO = response.getBody();
            OrderDTO orderDTO = orderMappper.toDto(order);
            return new OrderDTO(orderDTO.orderId(), orderDTO.price(), orderDTO.restauId(), restaurantDTO);
        }).orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        RestaurantDTO restaurantDTO = restaurantClient.getRestaurantById(orderDTO.restauId());
        if(restaurantDTO != null) {
            Order order = orderMappper.toEntity(orderDTO);
            orderRepository.save(order);
            return new OrderDTO(orderDTO.orderId(), orderDTO.price(), orderDTO.restauId(), restaurantDTO);
        }
        else throw new IllegalArgumentException("Restaurant not found");
    }

    public List<OrderDTO> findAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> {
                    OrderDTO orderDTO = orderMappper.toDto(order);
                    RestaurantDTO restaurantDTO = restaurantClient.getRestaurantById(orderDTO.restauId());
                    return new OrderDTO(orderDTO.orderId(), orderDTO.price(), orderDTO.restauId(), restaurantDTO);
                })
                .collect(Collectors.toList());
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    @KafkaListener(topics = "event-topic", groupId = "group_id")
    public void consumeEvent(String idRestau) {
        orderRepository.findAllByRestauranttId(idRestau).forEach(
                order -> deleteOrderById(order.getId())
        );
        log.info("Consumed event: {}", idRestau);
    }

}

