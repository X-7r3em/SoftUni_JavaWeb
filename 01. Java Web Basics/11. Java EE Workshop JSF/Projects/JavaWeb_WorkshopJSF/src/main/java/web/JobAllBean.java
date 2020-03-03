package web;

import domain.view.JobApplicationViewModel;
import org.modelmapper.ModelMapper;
import service.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class JobAllBean {
    private List<JobApplicationViewModel> jobs;

    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobAllBean() {
    }

    @Inject
    public JobAllBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        jobs = jobApplicationService.findAll().stream()
                .map(j -> modelMapper.map(j, JobApplicationViewModel.class))
                .collect(Collectors.toList());
    }

    public List<JobApplicationViewModel> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobApplicationViewModel> jobs) {
        this.jobs = jobs;
    }
}
