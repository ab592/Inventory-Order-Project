package com.koerber.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.koerber.order.client.InventoryClient;
import com.koerber.order.model.AllocatedBatchDto;
import com.koerber.order.model.InventoryBatchDto;
import com.koerber.order.serviceImpl.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
	
	@Mock
    private InventoryClient inventoryClient;

    @InjectMocks
    private OrderServiceImpl service;

    @Test
    void shouldAllocateInventoryFIFO() {

        List<InventoryBatchDto> batches = List.of(
            new InventoryBatchDto(5L, null, null, 39, LocalDate.of(2026, 3, 31)),
            new InventoryBatchDto(7L, null, null, 40, LocalDate.of(2026, 4, 24))
        );

        when(inventoryClient.getInventory(1005L)).thenReturn(batches);

        List<AllocatedBatchDto> result =
                service.allocate(1005L, 50);

        assertEquals(2, result.size());
        assertEquals(39, result.get(0).getQuantity());
        assertEquals(11, result.get(1).getQuantity());
    }

}
