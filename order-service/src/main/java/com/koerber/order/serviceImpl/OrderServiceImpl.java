package com.koerber.order.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.koerber.order.client.InventoryClient;
import com.koerber.order.model.AllocatedBatchDto;
import com.koerber.order.model.InventoryBatchDto;
import com.koerber.order.model.OrderRequest;
import com.koerber.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	private final InventoryClient inventoryClient;
	
	public OrderServiceImpl(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
        
    }
	
	@Override
    public List<AllocatedBatchDto> placeOrder(OrderRequest request) {
        return allocate(request.getProductId(), request.getOrderQuantity());
    }

	@Override
	public List<AllocatedBatchDto> allocate(Long productId, int quantity) {
		List<InventoryBatchDto> batches =
                inventoryClient.getInventory(productId);

        List<AllocatedBatchDto> allocated = new ArrayList<>();

        for (InventoryBatchDto batch : batches) {
            if (quantity <= 0) break;

            int allocatedQty = Math.min(batch.getQuantity(), quantity);

            AllocatedBatchDto allocatedBatch = new AllocatedBatchDto();
            allocatedBatch.setBatchId(batch.getBatchId());
            allocatedBatch.setQuantity(allocatedQty);
            allocatedBatch.setExpiryDate(batch.getExpiryDate());

            allocated.add(allocatedBatch);
            quantity -= allocatedQty;
        }

        if (quantity > 0) {
            throw new RuntimeException(
                "Insufficient inventory for productId " + productId
            );
        }

        return allocated;
	}

}
