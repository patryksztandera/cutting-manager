package com.cutting.manager.models.services;

import com.cutting.manager.models.entities.JobEntity;
import com.cutting.manager.models.repositories.JobRepository;
import com.cutting.manager.models.repositories.MetalSheetRepository;
import com.cutting.manager.models.responses.JobFxModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final MetalSheetRepository metalSheetRepository;

    public JobService(JobRepository jobRepository, MetalSheetRepository metalSheetRepository) {
        this.jobRepository = jobRepository;
        this.metalSheetRepository = metalSheetRepository;
    }

    public ObservableList<JobFxModel> getAll() {
        ObservableList<JobFxModel> jobFxModelObservableList = FXCollections.observableArrayList();

        jobRepository.findAll().forEach(e -> jobFxModelObservableList.add(new JobFxModel(e)));
        return jobFxModelObservableList;
    }

    public ObservableList<JobFxModel> getUnfinishedJobs() {
        ObservableList<JobFxModel> jobFxModelObservableList = FXCollections.observableArrayList();

        jobRepository.findByEndTimeIsNull().forEach(e -> jobFxModelObservableList.add(new JobFxModel(e)));
        return jobFxModelObservableList;
    }

    public ObservableList<JobFxModel> getFinishedJobs() {
        ObservableList<JobFxModel> jobFxModelObservableList = FXCollections.observableArrayList();

        jobRepository.findByEndTimeIsNotNull().forEach(e -> {
            JobFxModel jobFxModel = new JobFxModel(e);
            jobFxModel.setEndTime(e.getEndTime());
            jobFxModelObservableList.add(jobFxModel);
        });
        return jobFxModelObservableList;
    }

    public void add(JobFxModel model) {
        jobRepository.save(mapFxModel(model));
    }

    private JobEntity mapFxModel(JobFxModel model) {
        return new JobEntity(
                model.getName(),
                model.getFileType(),
                model.getImgByte(),
                metalSheetRepository.getById(model.getMetalSheetId()));
    }

    public void deleteById(Long id) {
        jobRepository.deleteById(id);
    }

    @Transactional
    public void setEndTime(Long id) {
        JobEntity entity = this.jobRepository.getOne(id);
        entity.setEndTime(ZonedDateTime.now());
        this.jobRepository.save(entity);
    }
}
