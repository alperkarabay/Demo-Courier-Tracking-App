package com.courier.tracking.dto;

import com.courier.tracking.entity.Courier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourierDto {
    private String name;
    private Double totalTraveledDistance;

    public Courier toEntity() {
        return Courier.builder()
                .name(this.getName())
                .build();
    }
}