package com.courier.tracking.service;


import com.courier.tracking.entity.Courier;
import com.courier.tracking.entity.CourierLocation;

import java.util.List;

public interface CourierService {
    Courier saveCourier(Courier courier);
    List<Courier> getAllCouriers();
    Courier getCourierById(Long id);
    Courier updateTotalDistance(Courier courier, CourierLocation lastLocation, CourierLocation currentLocation);
    Double getTotalTraveDistance(Long id);
}