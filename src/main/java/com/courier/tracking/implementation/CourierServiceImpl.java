package com.courier.tracking.implementation;

import com.courier.tracking.entity.Courier;
import com.courier.tracking.entity.CourierLocation;
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

    @Override
    public Courier getCourierById(Long id) {
        return courierRepository.findById(id).orElseThrow(() -> new RuntimeException("Courier not found"));
    }

    @Override
    public Courier updateTotalDistance(Courier courier, CourierLocation lastLocation, CourierLocation currentLocation) {
        return saveCourier(courier.updateTotalTraveledDistance(currentLocation, lastLocation));
    }

    @Override
    public Double getTotalTraveDistance(Long id) {
        return getCourierById(id).getTotalTraveledDistance();
    }


}