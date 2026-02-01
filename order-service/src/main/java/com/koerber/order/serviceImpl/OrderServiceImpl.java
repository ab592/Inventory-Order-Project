package com.koerber.order.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koerber.order.client.InventoryClient;
import com.koerber.order.model.AllocatedBatchDto;
import com.koerber.order.model.InventoryBatchDto;
import com.koerber.order.model.InventoryUpdateRequest;
import com.koerber.order.model.Order;
import com.koerber.order.model.OrderRequest;
import com.koerber.order.model.OrderResponseDto;
import com.koerber.order.repository.OrderRepository;
import com.koerber.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	private final InventoryClient inventoryClient;

	private final OrderRepository orderRepository;
	
	public OrderServiceImpl(InventoryClient inventoryClient, OrderRepository orderRepository) {
        this.inventoryClient = inventoryClient;
        this.orderRepository= orderRepository;
        
    }
	
	
	
	@Override
	public OrderResponseDto placeOrder(OrderRequest request) {

		List<AllocatedBatchDto> allocatedBatches =
	            allocate(request.getProductId(), request.getOrderQuantity());

	    // Build inventory update request
	    InventoryUpdateRequest updateRequest = new InventoryUpdateRequest();
	    updateRequest.setProductId(request.getProductId());

	    List<InventoryUpdateRequest.BatchUpdate> updates =
	            allocatedBatches.stream().map(b -> {
	                InventoryUpdateRequest.BatchUpdate u =
	                        new InventoryUpdateRequest.BatchUpdate();
	                u.setBatchId(b.getBatchId());
	                u.setQuantity(b.getQuantity());
	                return u;
	            }).toList();

	    updateRequest.setUpdates(updates);

	    inventoryClient.updateInventory(updateRequest);

	    OrderResponseDto response = new OrderResponseDto();
	    response.setOrderId(System.currentTimeMillis()); // acceptable for assignment
	    response.setProductId(request.getProductId());
	    response.setQuantity(request.getOrderQuantity());
	    response.setStatus("PLACED");
	    response.setReservedFromBatchIds(
	            allocatedBatches.stream()
	                    .map(AllocatedBatchDto::getBatchId)
	                    .toList()
	    );
	    response.setMessage("Order placed. Inventory reserved.");

	    return response;
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
