package app.service;

import app.domain.entities.Car;
import app.domain.models.service.CarServiceModel;
import app.domain.models.view.CarViewModel;
import app.repository.CarRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {
    private final ModelMapper modelMapper;

    @Inject
    public CarServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void createCar(CarServiceModel car) {
        Car carModel = this.modelMapper.map(car, Car.class);
        CarRepository.addCar(carModel);
    }

    @Override
    public List<CarViewModel> getCars() {
        return CarRepository.getCars()
                .stream()
                .map(c -> this.modelMapper.map(c, CarViewModel.class))
                .collect(Collectors.toList());
    }
}
