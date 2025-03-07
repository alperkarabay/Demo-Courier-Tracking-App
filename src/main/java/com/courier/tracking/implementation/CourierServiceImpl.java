package com.courier.tracking.implementation;

import com.courier.tracking.entity.Courier;
import com.courier.tracking.repository.CourierRepository;
import com.courier.tracking.service.CourierService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {
    private final CourierRepository courierRepository;

    @Override
    public Courier saveCourier(Courier courier) {
        return courierRepository.save(courier);
    }

    @Override
    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }
}