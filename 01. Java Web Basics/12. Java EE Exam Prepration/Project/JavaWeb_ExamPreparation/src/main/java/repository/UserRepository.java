package repository;

import domain.entity.User;

import java.util.List;

public interface UserRepository {
    void save(User user);

    void update(User user);

    User findById(String id);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    List<User> findAll();
}
