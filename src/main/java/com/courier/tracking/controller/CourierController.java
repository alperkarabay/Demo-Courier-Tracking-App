package com.courier.tracking.controller;

import com.courier.tracking.dto.CourierDto;
import com.courier.tracking.entity.Courier;
import com.courier.tracking.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/couriers")
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;

    @PostMapping
    public ResponseEntity<CourierDto> createCourier(@RequestBody CourierDto courierDto) {
        return ResponseEntity.ok(courierService.saveCourier(courierDto.toEntity()).toDto());
    }

    @GetMapping
    public ResponseEntity<List<CourierDto>> getAllCouriers() {
        return ResponseEntity.ok(courierService.getAllCouriers().stream().map(Courier::toDto).toList());
    }

}