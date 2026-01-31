package com.koerber.inventory.service.impl;

import com.koerber.inventory.DTO.InventoryResponseDto;
import com.koerber.inventory.Mapper.InventoryMapper;
import com.koerber.inventory.model.InventoryBatch;
import com.koerber.inventory.service.InventoryService;
import com.koerber.inventory.service.factory.InventoryStrategyFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryStrategyFactory factory;

    public InventoryServiceImpl(InventoryStrategyFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<InventoryResponseDto> getInventoryByProduct(Long productId) {
        return factory.getStrategy("expiryStrategy")
                .getBatches(productId)
                .stream()
                .map(InventoryMapper::toDto)
                .toList();
    }
}
