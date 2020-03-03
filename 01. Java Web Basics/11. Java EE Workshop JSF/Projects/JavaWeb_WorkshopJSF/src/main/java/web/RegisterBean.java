package web;

import domain.binding.UserRegisterBindingModel;
import domain.service.UserServiceModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class RegisterBean extends BaseBean {
    private UserRegisterBindingModel user;

    private UserService userService;
    private ModelMapper mapper;

    public RegisterBean() {
    }

    @Inject
    public RegisterBean(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostConstruct
    public void init() {
        user = new UserRegisterBindingModel();
    }


    public UserRegisterBindingModel getUser() {
        return user;
    }

    public void setUser(UserRegisterBindingModel user) {
        this.user = user;
    }

    public void register() {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return;
        }
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        userService.save(mapper.map(user, UserServiceModel.class));

        redirect("/login");
    }


}
