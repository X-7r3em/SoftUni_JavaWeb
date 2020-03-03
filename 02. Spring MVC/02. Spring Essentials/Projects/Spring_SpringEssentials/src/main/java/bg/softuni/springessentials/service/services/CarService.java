package bg.softuni.springessentials.service.services;

import bg.softuni.springessentials.service.models.CarServiceModel;

import java.util.List;

public interface CarService {

    void save(CarServiceModel model);

    List<CarServiceModel> getAll();
}
