package com.koerber.order.service;

import java.util.List;

import com.koerber.order.model.AllocatedBatchDto;

public interface OrderService {
	
	List<AllocatedBatchDto> allocate(Long productId, int quantity);
}
