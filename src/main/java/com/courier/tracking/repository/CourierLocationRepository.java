package com.courier.tracking.repository;
import com.courier.tracking.entity.CourierLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourierLocationRepository extends JpaRepository<CourierLocation, Long> {
    List<CourierLocation> findByCourierIdOrderByTimestampAsc(Long courierId);
    CourierLocation findByCourierId(Long courierId);
}

