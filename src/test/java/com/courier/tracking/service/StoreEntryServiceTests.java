package com.courier.tracking.service;

import com.courier.tracking.dto.CourierLocationDto;
import com.courier.tracking.entity.Courier;
import com.courier.tracking.entity.Store;
import com.courier.tracking.handler.DistanceStoreEntryCheckHandler;
import com.courier.tracking.handler.RedisStoreEntryCheckHandler;
import com.courier.tracking.implementation.CourierLocationServiceImpl;
import com.courier.tracking.implementation.CourierServiceImpl;
import com.courier.tracking.implementation.StoreServiceImpl;
import com.courier.tracking.logger.StoreEntryLogger;
import com.courier.tracking.repository.CourierLocationRepository;
import com.courier.tracking.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = org.mockito.quality.Strictness.LENIENT)
class StoreEntryServiceTests {

    @Mock
    private StoreEntryLogger storeEntryLogger;

    @Mock
    private RedisStoreEntryCheckHandler redisHandler;
    @Mock
    private CourierServiceImpl courierService;
    @Mock
    private StoreServiceImpl storeService;
    @Mock
    private CourierLocationRepository courierLocationRepository;
    @Mock
    private StoreRepository storeRepository;
    @Mock
    private DistanceStoreEntryCheckHandler distanceHandler;

    @InjectMocks
    private CourierLocationServiceImpl storeEntryService;

    private Courier testCourier;
    private Store testStore;
    private CourierLocationDto courierLocationDto;

    @BeforeEach
    void setUp() {
        testStore = new Store();
        testStore.setId(1L);
        testStore.setName("Test Store");
        testStore.setLatitude(40.9923);
        testStore.setLongitude(29.1244);

        courierLocationDto = new CourierLocationDto();
        courierLocationDto.setCourierId(1L);
        courierLocationDto.setLatitude(40.9923);
        courierLocationDto.setLongitude(29.1244);

        testCourier = new Courier();
        testCourier.setId(1L);
        testCourier.setName("Test Courier");
        testCourier.setCurrentLocation(courierLocationDto.toEntity(1L, testCourier));

    }

    @Test
    void shouldAllowEntryWhenNotInRedisAndWithinDistance() {
        when(courierLocationRepository.findByCourierId(anyLong())).thenReturn(testCourier.getCurrentLocation());
        when(redisHandler.checkEntry(testCourier, testStore,courierLocationDto)).thenReturn(true);
        when(distanceHandler.checkEntry(testCourier, testStore,courierLocationDto)).thenReturn(true);
        when(courierService.getCourierById(anyLong())).thenReturn(testCourier);
        when(storeService.getAllStores()).thenReturn(List.of(testStore));
        storeEntryService.updateLocation(courierLocationDto);
        verify(storeEntryLogger, times(1)).onCourierEnterStoreRadius(testCourier, testStore);

    }

    @Test
    void shouldDenyEntryWhenAlreadyInRedis() {
        when(courierLocationRepository.findByCourierId(anyLong())).thenReturn(testCourier.getCurrentLocation());
        when(redisHandler.checkEntry(testCourier, testStore,courierLocationDto)).thenReturn(false);
        when(distanceHandler.checkEntry(testCourier, testStore,courierLocationDto)).thenReturn(true);
        when(courierService.getCourierById(anyLong())).thenReturn(testCourier);
        when(storeService.getAllStores()).thenReturn(List.of(testStore));

        verify(storeEntryLogger, never()).onCourierEnterStoreRadius(testCourier, testStore);
    }
}
