package bg.softuni.springessentials.service.services;

import bg.softuni.springessentials.data.models.Car;
import bg.softuni.springessentials.data.repositories.CarRepository;
import bg.softuni.springessentials.service.models.CarServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper mapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper mapper) {
        this.carRepository = carRepository;
        this.mapper = mapper;
    }


    @Override
    public void save(CarServiceModel model) {
        this.carRepository.save(this.mapper.map(model, Car.class));
    }

    @Override
    public List<CarServiceModel> getAll() {
        return this.carRepository.findAll().stream()
                .map(c -> this.mapper.map(c, CarServiceModel.class))
                .collect(Collectors.toList());
    }
}
