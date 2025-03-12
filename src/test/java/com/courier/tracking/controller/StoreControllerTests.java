package com.courier.tracking.controller;

import com.courier.tracking.dto.StoreDto;
import com.courier.tracking.entity.Store;
import com.courier.tracking.implementation.StoreServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreControllerTests {
    @Mock
    private StoreServiceImpl storeService;
    @InjectMocks
    private StoreController storeController;

    @Test
    void shouldGetAllStores() {
        when(storeService.getAllStores()).thenReturn(
                List.of(Store.builder()
                        .id(1L)
                        .name("Test Store")
                        .latitude(1.0)
                        .longitude(1.0)
                        .build()));
        Assertions.assertInstanceOf(List.class, storeController.getAllStores().getBody().getDetail());
        List<StoreDto> testStores = (List<StoreDto>) storeController.getAllStores().getBody().getDetail();

        Assertions.assertEquals(1, testStores.size());
        Assertions.assertEquals("Test Store", testStores.get(0).getName());
    }
}
