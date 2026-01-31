package com.koerber.inventory.controller;

import com.koerber.inventory.DTO.InventoryResponseDto;
import com.koerber.inventory.model.InventoryBatch;
import com.koerber.inventory.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productId}")
    public List<InventoryResponseDto> getInventory(@PathVariable Long productId) {
        return inventoryService.getInventoryByProduct(productId);
    }
}
