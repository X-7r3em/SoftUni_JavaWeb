package repository;

import domain.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void update(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(user);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public User findById(String id) {
        try {
            return this.entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        try {
            this.entityManager.getTransaction().begin();
            User user = this.entityManager
                    .createQuery(
                            "SELECT u FROM User u " +
                                    "WHERE u.username = :username AND u.password = :password", User.class
                    )
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            this.entityManager.getTransaction().commit();
            return user;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            return this.entityManager
                    .createQuery(
                            "SELECT u FROM User u " +
                                    "WHERE u.username = :username", User.class
                    )
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return this.entityManager
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }
}
