package com.courier.tracking.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourierLocationDto {
    private Long id;
    private Long courierId;
    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;
}