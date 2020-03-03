package bg.softuni.heroes.service.services;

import bg.softuni.heroes.service.models.auth.LoginUserServiceModel;
import bg.softuni.heroes.service.models.auth.RegisterUserServiceModel;
import bg.softuni.heroes.service.models.UserSessionServiceModel;

public interface AuthService {
    void register(RegisterUserServiceModel model);
    UserSessionServiceModel login(LoginUserServiceModel model) throws Exception;
}
