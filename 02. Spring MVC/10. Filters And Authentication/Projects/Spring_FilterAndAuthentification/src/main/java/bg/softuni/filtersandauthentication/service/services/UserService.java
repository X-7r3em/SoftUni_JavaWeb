package bg.softuni.filtersandauthentication.service.services;

import bg.softuni.filtersandauthentication.data.models.User;
import bg.softuni.filtersandauthentication.service.models.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(UserServiceModel model);
}
