package com.courier.tracking.logger;

import com.courier.tracking.entity.Courier;
import com.courier.tracking.entity.Store;
import com.courier.tracking.observer.StoreEntryObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StoreEntryLogger implements StoreEntryObserver {
    @Override
    public void onCourierEnterStoreRadius(Courier courier, Store store) {
        log.info("[LOG] Courier {} entered to stores radius: {}", courier.getName(), store.getName());
    }
}