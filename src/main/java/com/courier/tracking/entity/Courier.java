package com.courier.tracking.entity;

import com.courier.tracking.dto.CourierDto;
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

    public CourierDto toDto() {
        return CourierDto.builder()
                .id(this.getId())
                .name(this.getName())
                .build();
    }
}