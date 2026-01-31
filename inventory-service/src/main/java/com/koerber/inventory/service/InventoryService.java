package com.koerber.inventory.service;

import java.util.List;

import com.koerber.inventory.DTO.InventoryResponseDto;

public interface InventoryService {

    List<InventoryResponseDto> getInventoryByProduct(Long productId);
}
