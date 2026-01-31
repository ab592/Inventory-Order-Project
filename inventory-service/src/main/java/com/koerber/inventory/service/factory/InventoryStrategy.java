package com.koerber.inventory.service.factory;

import com.koerber.inventory.model.InventoryBatch;

import java.util.List;

public interface InventoryStrategy {

    List<InventoryBatch> getBatches(Long productId);
}
