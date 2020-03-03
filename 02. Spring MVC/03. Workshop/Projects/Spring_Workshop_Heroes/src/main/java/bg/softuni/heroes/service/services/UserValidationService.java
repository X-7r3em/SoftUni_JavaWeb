package bg.softuni.heroes.service.services;

import bg.softuni.heroes.service.models.auth.RegisterUserServiceModel;

public interface UserValidationService {
    boolean isValid(RegisterUserServiceModel user);
}
