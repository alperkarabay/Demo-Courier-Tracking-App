package com.courier.tracking.observer;

import com.courier.tracking.entity.Courier;
import com.courier.tracking.entity.Store;
import com.courier.tracking.handler.DistanceStoreEntryCheckHandler;
import com.courier.tracking.logger.StoreEntryLogger;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

public class StoreEntrySubject {
    private final List<StoreEntryObserver> observers = new ArrayList<>();
    StoreEntryLogger storeEntryLogger; // Default Observer
    @PostConstruct
    public void init() {
        addObserver(storeEntryLogger); // Add default observer at startup
    }
    public void addObserver(StoreEntryObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(StoreEntryObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Courier courier, Store store) {
        for (StoreEntryObserver observer : observers) {
            observer.onCourierEnterStoreRadius(courier, store);
        }
    }

}