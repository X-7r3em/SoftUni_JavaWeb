package app.repository;

import app.domain.entities.Car;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class CarRepositoryImpl implements CarRepository {
    private final EntityManager entityManager;
    private final ModelMapper mapper;

    @Inject
    public CarRepositoryImpl(EntityManager entityManager, ModelMapper mapper) {
        this.entityManager = entityManager;
        this.mapper = mapper;
    }

    @Override
    public void save(Car car) {
        entityManager.getTransaction().begin();
        entityManager.persist(car);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Car> getCars() {
        return entityManager.createQuery("FROM Car", Car.class).getResultList();
    }
}
