package com.esprit.ms_order.repositories;

import com.esprit.ms_order.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAllByRestaurantId(String orderId);
}
