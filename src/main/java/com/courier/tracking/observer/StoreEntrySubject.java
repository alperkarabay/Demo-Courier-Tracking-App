package com.courier.tracking.observer;

import com.courier.tracking.entity.Courier;
import com.courier.tracking.entity.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreEntrySubject {
    private final List<StoreEntryObserver> observers = new ArrayList<>();

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