package com.courier.tracking.implementation;

import com.courier.tracking.dto.CourierLocationDto;
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
    public CourierLocation saveLocation(CourierLocationDto locationDTO) {
        CourierLocation location = locationDTO.toEntity();
        location.setId(courierLocationRepository.findByCourierId(locationDTO.getCourierId()).getId());

        return courierLocationRepository.save(location);
    }

    @Override
    public List<CourierLocation> getCourierLocations(Long courierId) {
        return courierLocationRepository.findByCourierIdOrderByTimestampAsc(courierId);
    }
}