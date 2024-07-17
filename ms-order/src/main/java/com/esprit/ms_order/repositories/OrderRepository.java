package com.esprit.ms_order.repositories;

import com.esprit.ms_order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByEventId(String orderId);
}
