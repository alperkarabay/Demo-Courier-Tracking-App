package com.courier.tracking.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Errors {

    NO_ERROR(0, "SUCCESS"),
    ERROR_INVALID_REQUEST(1, "Invalid request!"),
    ERROR_INVALID_DATETIME(2, "Invalid date time!"),
    ERROR_NO_ELEMENT(3, "No element found!"),
    ERROR_COURIER_NOT_FOUND(3, "Courier not found!");

    private final int value;
    private final String message;

    Errors(int value){
        this.value = value;
        this.message = "";
    }
}