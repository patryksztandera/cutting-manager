package com.cutting.manager.singleton;

import com.cutting.manager.models.responses.JobFxModel;
import org.springframework.stereotype.Component;

@Component
public final class JobSingleton {
    JobFxModel jobFxModel;
    private final static JobSingleton INSTANCE = new JobSingleton();

    private JobSingleton() {
    }

    public JobFxModel getJobFxModel() {
        return jobFxModel;
    }

    public void setJobFxModel(JobFxModel jobFxModel) {
        this.jobFxModel = jobFxModel;
    }

    public static JobSingleton getINSTANCE() {
        return INSTANCE;
    }
}
