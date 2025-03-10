package com.courier.tracking.controller;

import com.courier.tracking.dto.CourierDto;
import com.courier.tracking.entity.Courier;
import com.courier.tracking.enums.Errors;
import com.courier.tracking.model.CustomResponseEntity;
import com.courier.tracking.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/couriers")
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;

    @PostMapping
    public ResponseEntity<CustomResponseEntity> createCourier(@RequestBody CourierDto courierDto) {
        courierService.saveCourier(courierDto.toEntity());
        return ResponseEntity.ok(CustomResponseEntity.OK);
    }

    @GetMapping
    public ResponseEntity<CustomResponseEntity> getAllCouriers() {
        return ResponseEntity.ok(new CustomResponseEntity(Errors.NO_ERROR.getValue(), Errors.NO_ERROR.getMessage(),courierService.getAllCouriers().stream().map(Courier::toDto).toList()));
    }

}