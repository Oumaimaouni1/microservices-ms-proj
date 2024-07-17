package com.esprit.ms_order.services;

import com.esprit.ms_order.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO getOrderById(Long orderId);
    OrderDTO saveOrder(OrderDTO orderDTO);
    List<OrderDTO> findAllOrders();
    void deleteOrderById(Long orderId);
    void consumeEvent(String message);
}

