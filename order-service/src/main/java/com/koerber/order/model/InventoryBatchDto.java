package com.koerber.order.model;

import java.time.LocalDate;

public class InventoryBatchDto {

    private Long batchId;
    private Long productId;
    private String productName;
    private int quantity;
    private LocalDate expiryDate;
    
 
    public InventoryBatchDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InventoryBatchDto(Long batchId, Long productId, String productName, int quantity, LocalDate expiryDate) {
		super();
		this.batchId = batchId;
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.expiryDate = expiryDate;
	}

	// getters & setters
    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
