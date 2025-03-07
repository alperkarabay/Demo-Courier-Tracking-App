package com.courier.tracking.controller;

import com.courier.tracking.dto.CourierLocationDto;
import com.courier.tracking.service.CourierLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/couriers")
@RequiredArgsConstructor
public class CourierLocationController {
    private final CourierLocationService courierLocationService;

    public ResponseEntity<CourierLocationDto> updateCourierLocation(CourierLocationDto courierLocationDto) {
        return ResponseEntity.ok(courierLocationService.saveLocation(courierLocationDto).toDto());
    }
}
