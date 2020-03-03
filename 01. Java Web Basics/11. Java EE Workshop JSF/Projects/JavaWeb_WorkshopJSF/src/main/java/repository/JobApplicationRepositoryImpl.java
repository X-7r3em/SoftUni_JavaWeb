package repository;

import domain.entity.JobApplication;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class JobApplicationRepositoryImpl implements JobApplicationRepository {
    private final EntityManager entityManager;

    @Inject
    public JobApplicationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(JobApplication jobApplication) {
        entityManager.getTransaction().begin();
        entityManager.persist(jobApplication);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<JobApplication> findAll() {
        entityManager.getTransaction().begin();
        List<JobApplication> jobs = entityManager.createQuery("SELECT j FROM JobApplication j", JobApplication.class)
                .getResultList();
        entityManager.getTransaction().commit();

        return jobs;
    }

    @Override
    public JobApplication findById(String id) {
        try {
            entityManager.getTransaction().begin();
            JobApplication job = entityManager.createQuery("SELECT j FROM JobApplication j WHERE j.id = :id", JobApplication.class)
                    .setParameter("id", id)
                    .getSingleResult();
            entityManager.getTransaction().commit();
            return job;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void delete(String id) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM JobApplication j WHERE j.id =:id")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
}
