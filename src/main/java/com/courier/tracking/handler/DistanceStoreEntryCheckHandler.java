package com.courier.tracking.handler;

import com.courier.tracking.dto.CourierLocationDto;
import com.courier.tracking.entity.Courier;
import com.courier.tracking.entity.Store;
import com.courier.tracking.util.GeoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class  DistanceStoreEntryCheckHandler extends StoreEntryCheckHandler {
    private static final double ENTRY_RADIUS_METERS = 100;

    @Override
    public boolean checkEntry(Courier courier,Store store, CourierLocationDto courierLocationDto) {
        double distance = GeoUtils.calculateDistance(
                courierLocationDto.getLatitude(), courierLocationDto.getLongitude(),
                store.getLatitude(), store.getLongitude()
        );

        if (distance > ENTRY_RADIUS_METERS) {
            return false; //Courier is not in the store radius
        }

        //Move to the next handler
        if (nextHandler != null) {
            return nextHandler.checkEntry(courier, store, courierLocationDto);
        }
        return true;
    }
}

