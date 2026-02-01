package com.koerber.order.model;

import java.util.List;

//import com.koerber.inventory.model.InventoryUpdateRequest.BatchUpdate;

public class InventoryUpdateRequest {
	
	private Long productId;
    private List<BatchUpdate> updates;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<BatchUpdate> getUpdates() {
        return updates;
    }

    public void setUpdates(List<BatchUpdate> updates) {
        this.updates = updates;
    }

    public static class BatchUpdate {
        private Long batchId;
        private Integer quantity;

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
    }

}
