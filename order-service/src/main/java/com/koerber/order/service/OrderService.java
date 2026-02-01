package com.koerber.order.service;

import java.util.List;

import com.koerber.order.model.AllocatedBatchDto;
import com.koerber.order.model.OrderRequest;
import com.koerber.order.model.OrderResponseDto;

public interface OrderService {
	
	List<AllocatedBatchDto> allocate(Long productId, int quantity);
	
	OrderResponseDto placeOrder(OrderRequest request);
}
