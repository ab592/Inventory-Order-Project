package com.koerber.inventory.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.koerber.inventory.model.InventoryBatch;
import com.koerber.inventory.repository.InventoryBatchRepository;

@ExtendWith(MockitoExtension.class)
class ExpiryBasedInventoryStrategyTest {

    @Mock
    private InventoryBatchRepository repository;

    @InjectMocks
    private ExpiryBasedInventoryStrategy strategy;

    @Test
    void shouldReturnBatchesOrderedByExpiryDate() {
        // given
        Long productId = 1005L;

        InventoryBatch b1 = new InventoryBatch();
        b1.setBatchId(5L);
        b1.setProductId(productId);
        b1.setProductName("Smartwatch");
        b1.setQuantity(39);
        b1.setExpiryDate(LocalDate.of(2026, 3, 31));

        InventoryBatch b2 = new InventoryBatch();
        b2.setBatchId(7L);
        b2.setProductId(productId);
        b2.setProductName("Smartwatch");
        b2.setQuantity(40);
        b2.setExpiryDate(LocalDate.of(2026, 4, 24));

        InventoryBatch b3 = new InventoryBatch();
        b3.setBatchId(2L);
        b3.setProductId(productId);
        b3.setProductName("Smartwatch");
        b3.setQuantity(52);
        b3.setExpiryDate(LocalDate.of(2026, 5, 30));

        List<InventoryBatch> sortedList = List.of(b1, b2, b3);

        when(repository.findByProductIdOrderByExpiryDateAsc(productId))
                .thenReturn(sortedList);

        // when
        List<InventoryBatch> result = strategy.getBatches(productId);

        // then
        assertEquals(3, result.size());
        assertEquals(LocalDate.of(2026, 3, 31), result.get(0).getExpiryDate());
        assertEquals(LocalDate.of(2026, 5, 30), result.get(2).getExpiryDate());
    }
}

