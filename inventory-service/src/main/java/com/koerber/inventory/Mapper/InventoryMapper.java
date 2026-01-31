package com.koerber.inventory.Mapper;

import com.koerber.inventory.DTO.InventoryResponseDto;
import com.koerber.inventory.model.InventoryBatch;

public class InventoryMapper {
	
	public static InventoryResponseDto toDto(InventoryBatch entity) {
        InventoryResponseDto dto = new InventoryResponseDto();
        dto.setBatchId(entity.getBatchId());
        dto.setQuantity(entity.getQuantity());
        dto.setExpiryDate(entity.getExpiryDate());
        return dto;
    }

}
