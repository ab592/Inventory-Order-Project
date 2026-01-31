package com.koerber.order.model;

import java.time.LocalDate;

public class AllocatedBatchDto {
	
	private Long batchId;
    private int quantity;
    private LocalDate expiryDate;
    
    
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
    
    

}
