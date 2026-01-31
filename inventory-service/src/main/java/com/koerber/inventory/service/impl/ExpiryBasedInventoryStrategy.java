package com.koerber.inventory.service.impl;

import com.koerber.inventory.model.InventoryBatch;
import com.koerber.inventory.repository.InventoryBatchRepository;
import com.koerber.inventory.service.factory.InventoryStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("expiryStrategy")
public class ExpiryBasedInventoryStrategy implements InventoryStrategy {

    private final InventoryBatchRepository repository;

    public ExpiryBasedInventoryStrategy(InventoryBatchRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<InventoryBatch> getBatches(Long productId) {
        return repository.findByProductIdOrderByExpiryDateAsc(productId);
    }
}
