package app.repository;

import app.domain.entity.User;

import java.util.List;

public interface UserRepository {
    void save(User user);
    
    User getById(String id);

    User getByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAll();
}
