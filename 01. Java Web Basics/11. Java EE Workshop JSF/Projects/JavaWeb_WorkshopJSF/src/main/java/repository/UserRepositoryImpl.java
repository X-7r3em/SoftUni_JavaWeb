package repository;

import domain.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

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
    public User findByUsernameAndPassword(String username, String password) {
        try {
            this.entityManager.getTransaction().begin();
            User user = entityManager.createQuery("FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            this.entityManager.getTransaction().commit();
            return user;
        } catch (NoResultException ex){
            return null;
        }
    }
}
