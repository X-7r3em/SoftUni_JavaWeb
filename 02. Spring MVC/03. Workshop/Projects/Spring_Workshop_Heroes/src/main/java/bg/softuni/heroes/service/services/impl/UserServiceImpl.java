package bg.softuni.heroes.service.services.impl;

import bg.softuni.heroes.data.models.User;
import bg.softuni.heroes.data.repositories.UserRepository;
import bg.softuni.heroes.service.models.users.UserProfileServiceModel;
import bg.softuni.heroes.service.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserProfileServiceModel getUserShortDetails(String username) {
        User user = this.userRepository.findByUsername(username);
        return this.modelMapper.map(user, UserProfileServiceModel.class);
    }
}
