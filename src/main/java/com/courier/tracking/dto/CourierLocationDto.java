package com.courier.tracking.dto;


import com.courier.tracking.entity.Courier;
import com.courier.tracking.entity.CourierLocation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourierLocationDto {
    private Long courierId;

    @Max(value = 90, message = "Latitude must be less than or equal to 90")
    @Min(value = -90, message = "Latitude must be greater than or equal to -90")
    private Double latitude;

    @Max(value = 180, message = "Longitude must be less than or equal to 180")
    @Min(value = -180, message = "Longitude must be greater than or equal to -180")

    private Double longitude;
    private LocalDateTime timestamp;

    public CourierLocation toEntity(long id, Courier courier) {
        return CourierLocation.builder()
                .id(id)
                .courier(courier)
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .timestamp(LocalDateTime.now())
                .build();
    }
}