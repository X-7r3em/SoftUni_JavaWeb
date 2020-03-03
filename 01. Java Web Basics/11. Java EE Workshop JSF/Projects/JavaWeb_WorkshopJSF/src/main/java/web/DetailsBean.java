package web;

import domain.view.JobApplicationViewModel;
import org.modelmapper.ModelMapper;
import service.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class DetailsBean {
    private JobApplicationViewModel job;

    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public DetailsBean() {
    }

    @Inject
    public DetailsBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        job = modelMapper.map(jobApplicationService.getById(req.getParameter("id")), JobApplicationViewModel.class);
    }

    public JobApplicationViewModel getJob() {
        return job;
    }

    public void setJob(JobApplicationViewModel job) {
        this.job = job;
    }
}
