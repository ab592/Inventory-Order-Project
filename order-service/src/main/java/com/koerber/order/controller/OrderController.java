package com.koerber.order.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.koerber.order.model.AllocatedBatchDto;
import com.koerber.order.service.OrderService;


@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/allocate")
    public ResponseEntity<List<AllocatedBatchDto>> allocate(
            @RequestParam Long productId,
            @RequestParam int quantity) {

        return ResponseEntity.ok(
            service.allocate(productId, quantity)
        );
    }
}