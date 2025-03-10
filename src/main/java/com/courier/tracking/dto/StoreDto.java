package com.courier.tracking.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDto {
    private String name;

    @Max(value = 90, message = "Latitude must be less than or equal to 90")
    @Min(value = -90, message = "Latitude must be greater than or equal to -90")
    private Double latitude;

    @Max(value = 180, message = "Longitude must be less than or equal to 180")
    @Min(value = -180, message = "Longitude must be greater than or equal to -180")
    private Double longitude;

    public StoreDto toEntity() {
        return StoreDto.builder()
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .name(this.getName())
                .build();
    }
}