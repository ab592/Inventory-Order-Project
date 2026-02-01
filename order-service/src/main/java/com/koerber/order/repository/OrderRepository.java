package com.koerber.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koerber.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
}