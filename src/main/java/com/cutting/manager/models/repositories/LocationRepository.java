package com.cutting.manager.models.repositories;

import com.cutting.manager.models.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    LocationEntity getByLocation(String location);
}
