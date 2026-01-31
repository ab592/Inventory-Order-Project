package com.koerber.order.service;

import java.util.List;

import com.koerber.order.model.AllocatedBatchDto;
import com.koerber.order.model.OrderRequest;

public interface OrderService {
	
	List<AllocatedBatchDto> allocate(Long productId, int quantity);
	
	 List<AllocatedBatchDto> placeOrder(OrderRequest request);
}
