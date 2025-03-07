package com.courier.tracking.implementation;

import com.courier.tracking.entity.CourierLocation;
import com.courier.tracking.repository.CourierLocationRepository;
import com.courier.tracking.service.CourierLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierLocationServiceImpl implements CourierLocationService {
    private final CourierLocationRepository courierLocationRepository;

    @Override
    public void saveLocation(CourierLocation location) {
        courierLocationRepository.save(location);
    }

    @Override
    public List<CourierLocation> getCourierLocations(Long courierId) {
        return courierLocationRepository.findByCourierIdOrderByTimestampAsc(courierId);
    }
}