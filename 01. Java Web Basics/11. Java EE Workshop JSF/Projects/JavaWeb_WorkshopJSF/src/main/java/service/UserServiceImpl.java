package service;

import domain.binding.UserLoginBindingModel;
import domain.entity.User;
import domain.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import repository.UserRepository;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(UserServiceModel userServiceModel) {
        userRepository.save(modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(UserLoginBindingModel user) {
        User dbUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (dbUser == null) {
            return null;
        }

        return modelMapper.map(dbUser, UserServiceModel.class);
    }
}
