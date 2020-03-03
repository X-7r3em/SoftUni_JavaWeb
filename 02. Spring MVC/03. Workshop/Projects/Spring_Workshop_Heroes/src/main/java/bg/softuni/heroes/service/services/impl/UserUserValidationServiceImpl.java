package bg.softuni.heroes.service.services.impl;

import bg.softuni.heroes.data.repositories.UserRepository;
import bg.softuni.heroes.service.models.auth.RegisterUserServiceModel;
import bg.softuni.heroes.service.services.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUserValidationServiceImpl implements UserValidationService {
    private final UserRepository userRepository;

    @Autowired
    public UserUserValidationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(RegisterUserServiceModel user) {
        return this.isPasswordValid(user.getPassword(), user.getConfirmPassword())
                && this.isUsernameFree(user.getUsername())
                && this.isEmailFree(user.getEmail());
    }

    private boolean isPasswordValid(String password, String confirmPassword) {
        return password != null && !password.isEmpty() && password.equals(confirmPassword);
    }

    private boolean isUsernameFree(String username) {
        return !this.userRepository.existsByUsername(username);
    }

    private boolean isEmailFree(String email) {
        return !this.userRepository.existsByEmail(email);
    }
}
