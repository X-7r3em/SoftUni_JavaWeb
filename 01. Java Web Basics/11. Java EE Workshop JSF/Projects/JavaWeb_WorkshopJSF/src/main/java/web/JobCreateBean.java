package web;

import domain.binding.JobCreateBindingModel;
import domain.entity.Sector;
import domain.service.JobApplicationServiceModel;
import org.modelmapper.ModelMapper;
import service.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class JobCreateBean extends BaseBean {
    private JobCreateBindingModel job;

    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobCreateBean() {
    }

    @Inject
    public JobCreateBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        job = new JobCreateBindingModel();
    }

    public void create() {
        Sector sector = null;
        try {
            sector = Sector.valueOf(job.getSector().toUpperCase());
        } catch (IllegalArgumentException ex) {
            redirect("/jobs-add");
            return;
        }

        job.setSector(job.getSector().toUpperCase());
        jobApplicationService.save(modelMapper.map(job, JobApplicationServiceModel.class));

        redirect("/home");
    }

    public JobCreateBindingModel getJob() {
        return job;
    }

    public void setJob(JobCreateBindingModel job) {
        this.job = job;
    }
}
