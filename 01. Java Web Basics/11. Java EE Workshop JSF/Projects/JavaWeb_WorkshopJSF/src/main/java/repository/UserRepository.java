package repository;

import domain.entity.User;

public interface UserRepository {

    void save(User user);

    User findByUsernameAndPassword(String username, String password);
}
