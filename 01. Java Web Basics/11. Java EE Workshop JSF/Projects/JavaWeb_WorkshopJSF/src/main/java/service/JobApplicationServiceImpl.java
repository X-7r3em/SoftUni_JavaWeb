package service;

import domain.entity.JobApplication;
import domain.service.JobApplicationServiceModel;
import org.modelmapper.ModelMapper;
import repository.JobApplicationRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobApplicationServiceImpl implements JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, ModelMapper modelMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void save(JobApplicationServiceModel job) {
        jobApplicationRepository.save(modelMapper.map(job, JobApplication.class));
    }

    @Override
    public List<JobApplicationServiceModel> findAll() {
        return jobApplicationRepository.findAll().stream()
                .map(j -> modelMapper.map(j, JobApplicationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public JobApplicationServiceModel getById(String id) {
        JobApplication job = jobApplicationRepository.findById(id);
        if (job == null) {
            return null;
        }
        return modelMapper.map(job, JobApplicationServiceModel.class);
    }

    @Override
    public void delete(String id) {
        jobApplicationRepository.delete(id);
    }
}
