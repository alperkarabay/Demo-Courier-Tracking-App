package com.courier.tracking.service;

import com.courier.tracking.dto.CourierDto;
import com.courier.tracking.entity.Courier;
import com.courier.tracking.entity.CourierLocation;
import com.courier.tracking.implementation.CourierServiceImpl;
import com.courier.tracking.repository.CourierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourierServiceTests {

    @Mock
    private CourierRepository courierRepository;

    @InjectMocks
    private CourierServiceImpl courierService;

    private Courier testCourier;


    @BeforeEach
    void setUp() {
        testCourier = new Courier();
        testCourier.setId(1L);
        testCourier.setName("Test Courier");
        testCourier.setTotalTraveledDistance(0.0);
    }

    @Test
    void shouldSaveCourier() {
        when(courierRepository.save(any(Courier.class))).thenReturn(testCourier);

        Courier savedCourier = courierService.saveCourier(testCourier);

        assertNotNull(savedCourier);
        assertEquals("Test Courier", savedCourier.getName());
        verify(courierRepository, times(1)).save(testCourier);
    }

    @Test
    void shouldGetAllCouriers() {
        when(courierRepository.findAll()).thenReturn(List.of(testCourier));

        List<Courier> couriers = courierService.getAllCouriers();

        assertEquals(1, couriers.size());
        verify(courierRepository, times(1)).findAll();
    }

    @Test
    void shouldThrowExceptionWhenCourierNotFound() {
        when(courierRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> courierService.getCourierById(1L));
        assertEquals("Courier not found", exception.getMessage());
    }

    @Test
    void shouldUpdateTotalDistance(){
        CourierLocation lastLocation = new CourierLocation();
        lastLocation.setId(1L);
        lastLocation.setLatitude(30.0);
        lastLocation.setLongitude(30.0);

        CourierLocation currentLocation = new CourierLocation();
        currentLocation.setId(1L);
        currentLocation.setLatitude(40.0);
        currentLocation.setLongitude(40.0);

        this.testCourier.setCurrentLocation(lastLocation);

        when(courierRepository.save(any(Courier.class))).thenReturn(testCourier);

        Courier updatedCourier = courierService.updateTotalDistance(testCourier, lastLocation, currentLocation);

        assertNotNull(updatedCourier);
        assertEquals(1435332.8792700958, updatedCourier.getTotalTraveledDistance());
        verify(courierRepository, times(1)).save(updatedCourier);

    }
}
