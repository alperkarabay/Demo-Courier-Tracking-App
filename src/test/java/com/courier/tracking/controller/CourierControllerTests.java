package com.courier.tracking.controller;

import com.courier.tracking.dto.CourierDto;
import com.courier.tracking.entity.Courier;
import com.courier.tracking.implementation.CourierServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourierControllerTests {
    @Mock
    private CourierServiceImpl courierService;
    @InjectMocks
    private CourierController courierController;

    @Test
    void shouldGetAllCouriers() {
        when(courierService.getAllCouriers()).thenReturn(
                List.of(Courier.builder()
                        .id(1L)
                        .name("Test Courier")
                        .totalTraveledDistance(0.0)
                        .build()));
        Assertions.assertInstanceOf(List.class, courierController.getAllCouriers().getBody().getDetail());
        List<Courier> testCouriers = (List<Courier>) courierController.getAllCouriers().getBody().getDetail();

        Assertions.assertEquals(1, testCouriers.size());
        Assertions.assertEquals("Test Courier", testCouriers.get(0).getName());
    }

    @Test
    void shouldSaveCourier() {
        CourierDto courierDto = CourierDto.builder()
                .name("Test Courier")
                .totalTraveledDistance(0.0)
                .build();
        Courier testCourier = courierDto.toEntity();
        when(courierService.saveCourier(any(Courier.class))).thenReturn(testCourier);

        var response = courierController.createCourier(courierDto);
        Assertions.assertNotNull(response.getBody(), "Response body is null");
        Assertions.assertEquals(200, response.getStatusCodeValue(), "Status code is not 200");
        Assertions.assertEquals(0, response.getBody().getStatus(), "Status is not OK");
    }
}
