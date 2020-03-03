package bg.softuni.heroes.service.services;

import bg.softuni.heroes.service.models.users.UserProfileServiceModel;

public interface UserService {
    UserProfileServiceModel getUserShortDetails(String username);
}
