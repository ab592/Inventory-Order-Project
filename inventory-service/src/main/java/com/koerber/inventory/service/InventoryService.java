package com.koerber.inventory.service;

import java.util.List;

import com.koerber.inventory.DTO.InventoryResponseDto;
import com.koerber.inventory.model.InventoryUpdateRequest;

public interface InventoryService {

    List<InventoryResponseDto> getInventoryByProduct(Long productId);
    
    void updateInventory(InventoryUpdateRequest request);
}
