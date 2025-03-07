package com.courier.tracking.service;

import com.courier.tracking.entity.CourierLocation;

import java.util.List;

public interface CourierLocationService {
    void saveLocation(CourierLocation location);
    List<CourierLocation> getCourierLocations(Long courierId);
}