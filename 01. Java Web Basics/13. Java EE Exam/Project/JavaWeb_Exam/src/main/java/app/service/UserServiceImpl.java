package app.service;

import app.domain.entity.User;
import app.domain.model.service.UserServiceModel;
import app.repository.UserRepository;
import org.modelmapper.ModelMapper;

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
    public UserServiceModel getById(String id) {
        User userDb = this.userRepository.getById(id);
        if (userDb == null) {
            return null;
        }

        return this.modelMapper.map(userDb, UserServiceModel.class);
    }

    @Override
    public UserServiceModel getByUsernameAndPassword(String username, String password) {
        User userDb = this.userRepository.getByUsernameAndPassword(username, password);
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
    public UserServiceModel getByEmail(String email) {
        User userDb = this.userRepository.findByEmail(email);
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
