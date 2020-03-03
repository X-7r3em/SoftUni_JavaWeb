package bg.softuni.heroes.service.services.impl;

import bg.softuni.heroes.data.models.User;
import bg.softuni.heroes.data.repositories.UserRepository;
import bg.softuni.heroes.service.models.auth.LoginUserServiceModel;
import bg.softuni.heroes.service.models.auth.RegisterUserServiceModel;
import bg.softuni.heroes.service.models.UserSessionServiceModel;
import bg.softuni.heroes.service.services.AuthService;
import bg.softuni.heroes.service.services.HashingService;
import bg.softuni.heroes.service.services.UserValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserValidationService userValidationService;
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthServiceImpl(UserValidationService userValidationService, UserRepository userRepository,
                           HashingService hashingService, ModelMapper modelMapper) {
        this.userValidationService = userValidationService;
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(RegisterUserServiceModel model) {
        if (!this.userValidationService.isValid(model)) {
            return;
        }

        model.setPassword(this.hashingService.hash(model.getPassword()));
        this.userRepository.save(this.modelMapper.map(model, User.class));
    }

    @Override
    public UserSessionServiceModel login(LoginUserServiceModel model) throws Exception {
        String hashedPassword = this.hashingService.hash(model.getPassword());
        Optional<User> userOptional = this.userRepository.findByUsernameAndPassword(model.getUsername(), hashedPassword);
        if (userOptional.isEmpty()) {
            throw new Exception("Invalid user");
        }

        User user = userOptional.get();
        String heroName = user.getHero() == null
                ? null
                : user.getHero().getName();
        return new UserSessionServiceModel(model.getUsername(), heroName);
    }
}
