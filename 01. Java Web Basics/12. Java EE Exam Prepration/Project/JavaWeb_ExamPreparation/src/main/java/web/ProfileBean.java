package web;

import domain.model.view.UserViewModel;
import org.modelmapper.ModelMapper;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class ProfileBean {
    private UserViewModel model;

    private UserService userService;
    private ModelMapper modelMapper;

    public ProfileBean() {
    }

    @Inject
    public ProfileBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        String username = req.getParameter("username");
        this.model = this.modelMapper.map(this.userService.getByUsername(username), UserViewModel.class);
    }

    public UserViewModel getModel() {
        return model;
    }

    public void setModel(UserViewModel model) {
        this.model = model;
    }
}
