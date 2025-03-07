package com.courier.tracking.entity;
import com.courier.tracking.dto.CourierLocationDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "courier_location")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourierLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "courier_id", nullable = false)
    private Courier courier;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public CourierLocationDto toDto() {
        return CourierLocationDto.builder()
                .courierId(this.getCourier().getId())
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .timestamp(this.getTimestamp())
                .build();
    }

}
