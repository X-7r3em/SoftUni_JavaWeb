package web;

import domain.binding.UserLoginBindingModel;
import domain.service.UserServiceModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginBean extends BaseBean {
    private UserLoginBindingModel user;

    private UserService userService;
    private ModelMapper mapper;

    public LoginBean() {
    }

    @Inject
    public LoginBean(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostConstruct
    public void init() {
        user = new UserLoginBindingModel();
    }

    public void login() {
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        UserServiceModel currentUser = userService.findUserByUsernameAndPassword(user);

        if (currentUser == null) {
            redirect("/login");
        } else {
            startSession(currentUser.getUsername());
            redirect("/home");
        }
    }

    public UserLoginBindingModel getUser() {
        return user;
    }

    public void setUser(UserLoginBindingModel user) {
        this.user = user;
    }
}
