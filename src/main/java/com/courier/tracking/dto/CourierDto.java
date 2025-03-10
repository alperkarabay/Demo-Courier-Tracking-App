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
public class CourierDto {
    private String name;
    private Double totalTraveledDistance = (double) 0;

    public Courier toEntity() {
        Courier courier = Courier.builder()
                .totalTraveledDistance(this.getTotalTraveledDistance())
                .name(this.getName())
                .build();
        courier.setCurrentLocation(CourierLocation.builder()
                .courier(courier)
                .longitude((double)0)
                .latitude((double)0)
                .timestamp(LocalDateTime.now())
                .build());
        return courier;
    }

}