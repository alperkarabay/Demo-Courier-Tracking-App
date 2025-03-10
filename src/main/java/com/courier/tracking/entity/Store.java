package com.courier.tracking.entity;

import com.courier.tracking.dto.StoreDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    public StoreDto toDto() {
        return StoreDto.builder()
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .name(this.getName())
                .build();
    }
}