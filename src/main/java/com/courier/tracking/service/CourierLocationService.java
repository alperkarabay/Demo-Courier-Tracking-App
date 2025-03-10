package com.courier.tracking.service;

import com.courier.tracking.dto.CourierLocationDto;
import com.courier.tracking.entity.CourierLocation;

import java.util.List;

public interface CourierLocationService {
    CourierLocation saveLocation(CourierLocationDto location);
    List<CourierLocation> getCourierLocations(Long courierId);
    CourierLocation updateLocation(CourierLocationDto courierLocationDto);
    CourierLocation getCourierLocation(Long courierId);
}