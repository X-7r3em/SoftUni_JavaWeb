package app.web;

import app.domain.model.binding.UserLoginBindingModel;
import app.domain.model.service.UserServiceModel;
import app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named
@ManagedBean
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

        sessionMap.put("username", userDb.getUsername());
        sessionMap.put("id", userDb.getId());

        this.redirect("/home");
    }

    public UserLoginBindingModel getUser() {
        return user;
    }

    public void setUser(UserLoginBindingModel user) {
        this.user = user;
    }
}
