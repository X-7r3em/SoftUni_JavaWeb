package service;

import domain.service.JobApplicationServiceModel;

import java.util.List;

public interface JobApplicationService {
    void save(JobApplicationServiceModel job);

    List<JobApplicationServiceModel> findAll();

    JobApplicationServiceModel getById(String id);

    void delete(String id);
}
