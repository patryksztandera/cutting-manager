package com.cutting.manager.models.repositories;

import com.cutting.manager.models.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {
    List<JobEntity> findByEndTimeIsNull();
    List<JobEntity> findByEndTimeIsNotNull();
}
