package com.courier.tracking.controller;

import com.courier.tracking.dto.CourierLocationDto;
import com.courier.tracking.enums.Errors;
import com.courier.tracking.model.CustomResponseEntity;
import com.courier.tracking.service.CourierLocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/couriers")
@RequiredArgsConstructor
public class CourierLocationController {
    private final CourierLocationService courierLocationService;

    @PostMapping("/observe")
    public ResponseEntity<CustomResponseEntity> observeCourierLocation(@Valid @RequestBody CourierLocationDto courierLocationDto) {
        CourierLocationDto location = courierLocationService.updateLocation(courierLocationDto).toDto();
        return ResponseEntity.ok(new CustomResponseEntity(Errors.NO_ERROR.getValue(),Errors.NO_ERROR.getMessage(),location));
    }
}
