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
    private Long id;
    private String name;

    public Courier toEntity() {
        return Courier.builder()
                .id(this.getId())
                .name(this.getName())
                .build();
    }
}