package com.koerber.inventory.DTO;

import java.time.LocalDate;

public class InventoryResponseDto {
	
	private Long batchId;
    private Integer quantity;
    private LocalDate expiryDate;
    
    
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
    
    

}
