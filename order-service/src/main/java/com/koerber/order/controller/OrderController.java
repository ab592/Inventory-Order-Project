package com.koerber.order.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PostExchange;

import com.koerber.order.model.AllocatedBatchDto;
import com.koerber.order.model.InventoryBatchDto;
import com.koerber.order.model.OrderRequest;
import com.koerber.order.model.OrderResponseDto;
import com.koerber.order.service.OrderService;


@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<OrderResponseDto> placeOrder(
            @RequestBody OrderRequest request) {
        return ResponseEntity.ok(service.placeOrder(request));
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