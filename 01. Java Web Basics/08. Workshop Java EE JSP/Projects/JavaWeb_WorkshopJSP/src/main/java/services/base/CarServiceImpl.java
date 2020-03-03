package services.base;

import models.entity.Car;
import models.entity.Engine;
import models.entity.User;
import models.service.CarServiceModel;
import org.modelmapper.ModelMapper;
import services.CarService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {
    private final EntityManager entityManager;
    private final ModelMapper mapper;

    @Inject
    public CarServiceImpl(EntityManager entityManager, ModelMapper mapper) {
        this.entityManager = entityManager;
        this.mapper = mapper;
    }


    @Override
    public List<CarServiceModel> getAll() {
        List<Car> cars = entityManager.createQuery("FROM Car c", Car.class).getResultList();
        return cars.stream()
                .map(c -> mapper.map(c, CarServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createCar(String brand, String model, String year, String engine, String username) {
        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setYear(year);
        car.setEngine(Engine.valueOf(engine.toUpperCase()));
        User user = entityManager
                .createQuery("FROM User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList().get(0);
        car.setUser(user);

        entityManager.getTransaction().begin();
        entityManager.persist(car);
        entityManager.getTransaction().commit();
    }
}
