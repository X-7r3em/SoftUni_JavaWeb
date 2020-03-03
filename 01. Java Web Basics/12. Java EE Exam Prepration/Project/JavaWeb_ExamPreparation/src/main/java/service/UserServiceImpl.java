package service;

import domain.entity.User;
import domain.model.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import repository.UserRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserServiceModel user) {
        this.userRepository.save(this.modelMapper.map(user, User.class));
    }

    @Override
    public void update(UserServiceModel user) {
        this.userRepository.update(this.modelMapper.map(user, User.class));
    }

    @Override
    public UserServiceModel getById(String id) {
        User userDb = this.userRepository.findById(id);
        if (userDb == null) {
            return null;
        }

        return this.modelMapper.map(userDb, UserServiceModel.class);
    }

    @Override
    public UserServiceModel getByUsernameAndPassword(String username, String password) {
        User userDb = this.userRepository.findByUsernameAndPassword(username, password);
        if (userDb == null) {
            return null;
        }

        return this.modelMapper.map(userDb, UserServiceModel.class);
    }

    @Override
    public UserServiceModel getByUsername(String username) {
        User userDb = this.userRepository.findByUsername(username);
        if (userDb == null) {
            return null;
        }

        return this.modelMapper.map(userDb, UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> getAll() {
        return this.userRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }
}
