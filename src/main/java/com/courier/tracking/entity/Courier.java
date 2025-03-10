package com.courier.tracking.entity;

import com.courier.tracking.dto.CourierDto;
import com.courier.tracking.util.GeoUtils;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "courier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Double totalTraveledDistance;

    public CourierDto toDto() {
        return CourierDto.builder()
                .id(this.getId())
                .name(this.getName())
                .totalTraveledDistance(this.getTotalTraveledDistance())
                .build();
    }

    public Courier updateTotalTraveledDistance(CourierLocation currentCourierLocation, CourierLocation previousCourierLocation) {
        this.totalTraveledDistance += GeoUtils.calculateDistance(
                currentCourierLocation.getLatitude(), currentCourierLocation.getLongitude(),
                previousCourierLocation.getLatitude(), previousCourierLocation.getLongitude()
        );
        return this;
    }
}