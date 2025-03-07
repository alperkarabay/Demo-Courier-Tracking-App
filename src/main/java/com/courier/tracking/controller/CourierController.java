package com.courier.tracking.controller;

import com.courier.tracking.dto.CourierDto;
import com.courier.tracking.dto.CourierLocationDto;
import com.courier.tracking.entity.Courier;
import com.courier.tracking.entity.CourierLocation;
import com.courier.tracking.service.CourierLocationService;
import com.courier.tracking.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/couriers")
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;
    private final CourierLocationService courierLocationService;

    @PostMapping
    public ResponseEntity<CourierDto> createCourier(@RequestBody CourierDto courierDto) {
        return ResponseEntity.ok(courierService.saveCourier(courierDto.toEntity()).toDto());
    }

    @GetMapping
    public ResponseEntity<List<CourierDto>> getAllCouriers() {
        return ResponseEntity.ok(courierService.getAllCouriers().stream().map(Courier::toDto).toList());
    }

}