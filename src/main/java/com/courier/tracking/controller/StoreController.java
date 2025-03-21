package com.courier.tracking.controller;


import com.courier.tracking.dto.StoreDto;
import com.courier.tracking.entity.Store;
import com.courier.tracking.enums.Errors;
import com.courier.tracking.model.CustomResponseEntity;
import com.courier.tracking.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<CustomResponseEntity> getAllStores() {
        List<StoreDto> stores = storeService.getAllStores().stream().map(Store::toDto).toList();
        return ResponseEntity.ok(new CustomResponseEntity(Errors.NO_ERROR.getValue(), Errors.NO_ERROR.getMessage(), stores));
    }
}