package com.koerber.order.client;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.koerber.order.model.InventoryBatchDto;

@Component
public class InventoryClient {

    private final RestTemplate restTemplate;

    public InventoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<InventoryBatchDto> getInventory(Long productId) {
        String url = "http://localhost:8080/inventory/" + productId;
        InventoryBatchDto[] response =
                restTemplate.getForObject(url, InventoryBatchDto[].class);
        return List.of(response);
    }
    
}