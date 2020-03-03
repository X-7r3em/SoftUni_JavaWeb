package app.repository;

import app.domain.entity.Hero;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class HeroRepositoryImpl implements HeroRepository {
    private EntityManager entityManager;

    @Inject
    public HeroRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Hero hero) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(hero);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void delete(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery("DELETE FROM Hero h WHERE h.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }

    @Override
    public Hero getById(String id) {
        try {
            return this.entityManager.createQuery("SELECT h FROM Hero h WHERE h.id = :id", Hero.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Hero getByName(String name) {
        try {
            return this.entityManager.createQuery("SELECT h FROM Hero h WHERE h.name = :name", Hero.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Hero> getAll() {
        return this.entityManager
                .createQuery("SELECT h from Hero h", Hero.class)
                .getResultList();
    }
}
