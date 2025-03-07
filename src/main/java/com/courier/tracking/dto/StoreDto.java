package com.courier.tracking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDto {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;

    public StoreDto toEntity() {
        return StoreDto.builder()
                .id(this.getId())
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .name(this.getName())
                .build();
    }
}