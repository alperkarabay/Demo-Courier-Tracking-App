package com.courier.tracking.service;

import com.courier.tracking.entity.Store;
import com.courier.tracking.implementation.StoreServiceImpl;
import com.courier.tracking.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreServiceTests {
    @Mock
    private StoreRepository storeRepository;
    @InjectMocks
    private StoreServiceImpl storeService;

    private Store testStore;

    @BeforeEach
    void setUp() {
        testStore = new Store();
        testStore.setId(1L);
        testStore.setName("Test Store");
    }

    @Test
    void shouldGetAllStores() {
        when(storeRepository.findAll()).thenReturn(List.of(testStore));
        List<Store> testStores = storeService.getAllStores();
        assertEquals(1, testStores.size());
        assertEquals("Test Store", testStores.get(0).getName());
    }

}
