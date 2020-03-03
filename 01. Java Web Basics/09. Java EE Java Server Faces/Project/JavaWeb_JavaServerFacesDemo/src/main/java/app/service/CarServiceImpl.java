package app.service;

import app.domain.entities.Car;
import app.domain.models.service.CarServiceModel;
import app.repository.CarRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper mapper;

    @Inject
    public CarServiceImpl(CarRepository carRepository, ModelMapper mapper) {
        this.carRepository = carRepository;
        this.mapper = mapper;
    }

    @Override
    public void saveCar(CarServiceModel carServiceModel) {
        carRepository.save(mapper.map(carServiceModel, Car.class));
    }

    @Override
    public List<CarServiceModel> getAllCars() {
        return carRepository.getCars().stream()
                .map(c -> mapper.map(c, CarServiceModel.class))
                .sorted(Comparator.comparing(CarServiceModel::getBrand))
                .collect(Collectors.toList());
    }
}
