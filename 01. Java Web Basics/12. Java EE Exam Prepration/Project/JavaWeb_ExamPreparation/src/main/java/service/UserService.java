package service;

import domain.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    void register(UserServiceModel user);

    void update(UserServiceModel user);

    UserServiceModel getById(String id);

    UserServiceModel getByUsernameAndPassword(String username, String password);

    UserServiceModel getByUsername(String username);

    List<UserServiceModel> getAll();
}
