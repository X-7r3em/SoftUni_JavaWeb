package service;

import domain.binding.UserLoginBindingModel;
import domain.service.UserServiceModel;

public interface UserService {
    void save(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(UserLoginBindingModel user);
}
