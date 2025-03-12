package com.courier.tracking.model;

import com.courier.tracking.enums.Errors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponseEntity {
    private Integer status;
    private String message;
    private Object detail;

    public static final CustomResponseEntity OK = new CustomResponseEntity(Errors.NO_ERROR.getValue(),"OK");
    public static final CustomResponseEntity ERROR_INVALID_REQUEST = new CustomResponseEntity(Errors.ERROR_INVALID_REQUEST.getValue(),Errors.ERROR_INVALID_REQUEST.getMessage());
    public static final CustomResponseEntity ERROR_INVALID_DATETIME = new CustomResponseEntity(Errors.ERROR_INVALID_DATETIME.getValue(),Errors.ERROR_INVALID_DATETIME.getMessage());
    public static final CustomResponseEntity ERROR_NO_ELEMENT = new CustomResponseEntity(Errors.ERROR_NO_ELEMENT.getValue(),Errors.ERROR_NO_ELEMENT.getMessage());
    public static final CustomResponseEntity ERROR_COURIER_NOT_FOUND = new CustomResponseEntity(Errors.ERROR_COURIER_NOT_FOUND.getValue(),Errors.ERROR_COURIER_NOT_FOUND.getMessage());

    public CustomResponseEntity(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public CustomResponseEntity(CustomResponseEntity response, Object result) {
        this.status = response.getStatus();
        this.message = response.getMessage();
        this.detail = result;
    }


}