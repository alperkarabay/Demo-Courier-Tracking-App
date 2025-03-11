package com.courier.tracking.service;

import com.courier.tracking.entity.Courier;
import com.courier.tracking.entity.CourierLocation;
import com.courier.tracking.implementation.CourierLocationServiceImpl;
import com.courier.tracking.implementation.CourierServiceImpl;
import com.courier.tracking.repository.CourierLocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourierLocationServiceTests {

    @Mock
    private CourierLocationRepository courierLocationRepository;
    @Mock
    private CourierServiceImpl courierService;

    @InjectMocks
    private CourierLocationServiceImpl courierLocationService;

    private Courier testCourier;
    private CourierLocation testLocation;

    @BeforeEach
    void setUp() {
        testCourier = new Courier();
        testCourier.setId(1L);
        testCourier.setName("Test Courier");

        testLocation = new CourierLocation();
        testLocation.setId(1L);
        testLocation.setCourier(testCourier);
        testLocation.setLatitude(40.9923);
        testLocation.setLongitude(29.1244);
        testLocation.setTimestamp(LocalDateTime.now());
    }

    @Test
    void shouldSaveCourierLocation() {
        when(courierLocationRepository.findByCourierId(anyLong())).thenReturn(testLocation);
        courierLocationService.saveLocation(testLocation.toDto(),testCourier);

        verify(courierLocationRepository, times(1)).findByCourierId(1L);
    }

    @Test
    void shouldGetCourierLocations() {
        when(courierLocationRepository.findByCourierIdOrderByTimestampAsc(1L)).thenReturn(List.of(testLocation));

        List<CourierLocation> locations = courierLocationService.getCourierLocations(1L);

        assertEquals(1, locations.size());
        verify(courierLocationRepository, times(1)).findByCourierIdOrderByTimestampAsc(1L);
    }
}
