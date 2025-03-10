package com.courier.tracking.dto;


import com.courier.tracking.entity.Courier;
import com.courier.tracking.entity.CourierLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourierLocationDto {
    private Long courierId;
    private Double latitude;
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