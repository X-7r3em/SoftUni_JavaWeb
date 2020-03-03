package services.base;

import models.entity.User;
import services.UsersValidationService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UsersValidationServiceImpl implements UsersValidationService {
    public static final String PATTERN = "^(.+)@(.+)$";

    private final EntityManager entityManager;

    @Inject
    public UsersValidationServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean validateUser(String username, String email, String password, String confirmPassword) {
        return isUsernameFree(username)
                && isEmailValid(email)
                && arePasswordsMatching(password, confirmPassword);
    }

    private boolean arePasswordsMatching(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isEmailValid(String email) {
        List<User> users = entityManager.createQuery("FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();

        return email.matches(PATTERN) && users.isEmpty();
    }

    private boolean isUsernameFree(String username) {
        List<User> users = entityManager.createQuery("FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();

        return users.isEmpty();
    }
}
