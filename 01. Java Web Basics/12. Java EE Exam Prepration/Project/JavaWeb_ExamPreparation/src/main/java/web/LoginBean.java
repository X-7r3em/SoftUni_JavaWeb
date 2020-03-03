package web;

import domain.model.binding.UserLoginBindingModel;
import domain.model.service.UserServiceModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named
@RequestScoped
public class LoginBean extends BaseBean {
    private UserLoginBindingModel user;

    private UserService userService;
    private ModelMapper modelMapper;

    public LoginBean() {
    }

    @Inject
    public LoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.user = new UserLoginBindingModel();
    }

    public void login() {
        UserServiceModel userDb = this.userService
                .getByUsernameAndPassword(this.user.getUsername(), DigestUtils.sha256Hex(this.user.getPassword()));

        if (userDb == null) {
            this.redirect("/login");
            return;
        }

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap();

        sessionMap.put("username", this.user.getUsername());
        sessionMap.put("userId", userDb.getId());

        this.redirect("/home");
    }

    public UserLoginBindingModel getUser() {
        return user;
    }

    public void setUser(UserLoginBindingModel user) {
        this.user = user;
    }
}
