package com.courier.tracking.handler;

import com.courier.tracking.entity.Courier;
import com.courier.tracking.entity.Store;

public abstract class StoreEntryCheckHandler {
    protected StoreEntryCheckHandler nextHandler;

    public void setNextHandler(StoreEntryCheckHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract boolean checkEntry(Courier courier, Store store);
}
