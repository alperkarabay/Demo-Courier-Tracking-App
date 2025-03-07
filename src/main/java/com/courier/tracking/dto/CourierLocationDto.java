package com.courier.tracking.dto;


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
    private Long id;
    private Long courierId;
    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;

    public CourierLocation toEntity() {
        return CourierLocation.builder()
                .id(this.getId())
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .timestamp(this.getTimestamp())
                .build();
    }
}