package repository;

import domain.entity.JobApplication;

import java.util.List;

public interface JobApplicationRepository {

    void save(JobApplication jobApplication);

    List<JobApplication> findAll();

    JobApplication findById(String id);

    void delete(String id);
}
