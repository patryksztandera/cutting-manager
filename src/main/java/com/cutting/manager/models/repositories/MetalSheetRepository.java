package com.cutting.manager.models.repositories;

import com.cutting.manager.models.entities.MetalSheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public interface MetalSheetRepository extends JpaRepository<MetalSheetEntity, Long> {
    void deleteByTimestamp(ZonedDateTime timestamp);
}
