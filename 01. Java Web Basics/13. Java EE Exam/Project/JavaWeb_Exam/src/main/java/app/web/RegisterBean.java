package app.web;

import app.domain.model.binding.UserRegisterBindingModel;
import app.domain.model.service.UserServiceModel;
import app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class RegisterBean extends BaseBean {
    private UserRegisterBindingModel user;

    private UserService userService;
    private ModelMapper modelMapper;

    public RegisterBean() {
    }

    @Inject
    public RegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.user = new UserRegisterBindingModel();
    }

    public void register() {
        if (!this.user.getPassword().equals(this.user.getConfirmPassword())
                || this.userService.getByUsername(this.user.getUsername()) != null
                || this.userService.getByEmail(this.user.getEmail()) != null) {
            this.redirect("/register");
            return;
        }

        this.user.setPassword(DigestUtils.sha256Hex(this.user.getPassword()));
        this.userService.register(this.modelMapper.map(this.user, UserServiceModel.class));
        this.redirect("/login");
    }

    public UserRegisterBindingModel getUser() {
        return user;
    }

    public void setUser(UserRegisterBindingModel user) {
        this.user = user;
    }
}
