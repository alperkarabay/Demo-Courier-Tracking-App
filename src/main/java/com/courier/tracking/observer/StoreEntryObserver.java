package com.courier.tracking.observer;

import com.courier.tracking.entity.Courier;
import com.courier.tracking.entity.Store;

public interface StoreEntryObserver {
    void onCourierEnterStoreRadius(Courier courier, Store store);
}
