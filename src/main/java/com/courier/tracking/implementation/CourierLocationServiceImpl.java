package com.courier.tracking.implementation;

import com.courier.tracking.dto.CourierLocationDto;
import com.courier.tracking.entity.Courier;
import com.courier.tracking.entity.CourierLocation;
import com.courier.tracking.entity.Store;
import com.courier.tracking.handler.DistanceStoreEntryCheckHandler;
import com.courier.tracking.handler.RedisStoreEntryCheckHandler;
import com.courier.tracking.logger.StoreEntryLogger;
import com.courier.tracking.observer.StoreEntrySubject;
import com.courier.tracking.repository.CourierLocationRepository;
import com.courier.tracking.service.CourierLocationService;
import com.courier.tracking.service.CourierService;
import com.courier.tracking.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourierLocationServiceImpl implements CourierLocationService {
    private final CourierLocationRepository courierLocationRepository;
    private final CourierService courierService;
    private final StoreService storeService;
    private final StoreEntryLogger storeEntryLogger;
    private final DistanceStoreEntryCheckHandler distanceStoreEntryCheckHandler;
    private final RedisStoreEntryCheckHandler redisStoreEntryCheckHandler;

    @Override
    public CourierLocation saveLocation(CourierLocationDto locationDTO,Courier courier) {
        CourierLocation lastLocation = courierLocationRepository.findByCourierId(courier.getId());
        CourierLocation currentLocation = locationDTO.toEntity(lastLocation.getId(),courier);
        courierService.updateTotalDistance(courier, lastLocation, currentLocation);
        return courierLocationRepository.save(currentLocation);
    }

    @Override
    public List<CourierLocation> getCourierLocations(Long courierId) {
        return courierLocationRepository.findByCourierIdOrderByTimestampAsc(courierId);
    }

    @Override
    public CourierLocation getCourierLocation(Long courierId) {
        return courierLocationRepository.findByCourierId(courierId);
    }

    @Override
    public CourierLocation updateLocation(CourierLocationDto courierLocationDto) {
        Courier courier = courierService.getCourierById(courierLocationDto.getCourierId());
        List<Store> stores = storeService.getAllStores();
        StoreEntrySubject storeEntrySubject = new StoreEntrySubject();
        for (Store store : stores) {
            distanceStoreEntryCheckHandler.setNextHandler(redisStoreEntryCheckHandler);
            if(distanceStoreEntryCheckHandler.checkEntry(courier, store, courierLocationDto)) {
                storeEntryLogger.onCourierEnterStoreRadius(courier, store);
                storeEntrySubject.notifyObservers(courier, store);
            }
        }
        return saveLocation(courierLocationDto, courier);
    }
}