package com.koerber.inventory.service.impl;

import com.koerber.inventory.DTO.InventoryResponseDto;

import com.koerber.inventory.Mapper.InventoryMapper;
import com.koerber.inventory.model.InventoryBatch;
import com.koerber.inventory.repository.InventoryBatchRepository;
import com.koerber.inventory.service.InventoryService;
import com.koerber.inventory.service.factory.InventoryStrategyFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryStrategyFactory factory;
    private final InventoryBatchRepository repository;

    public InventoryServiceImpl(
            InventoryStrategyFactory factory,
            InventoryBatchRepository repository
    ) {
        this.factory = factory;
        this.repository = repository;
    }

    @Override
    public List<InventoryResponseDto> getInventoryByProduct(Long productId) {
        return factory.getStrategy("expiryStrategy")
                .getBatches(productId)
                .stream()
                .map(InventoryMapper::toDto)
                .toList();
    }

  

	@Override
	@Transactional
	public void updateInventory(com.koerber.inventory.model.InventoryUpdateRequest request) {
		for (com.koerber.inventory.model.InventoryUpdateRequest.BatchUpdate update : request.getUpdates()) {

            InventoryBatch batch = repository.findByBatchId(update.getBatchId())
                    .orElseThrow(() ->
                            new RuntimeException("Batch not found: " + update.getBatchId()));

            if (batch.getQuantity() < update.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient quantity in batch " + update.getBatchId()
                );
            }

            batch.setQuantity(batch.getQuantity() - update.getQuantity());
            repository.save(batch);
        }
		
	}
}
