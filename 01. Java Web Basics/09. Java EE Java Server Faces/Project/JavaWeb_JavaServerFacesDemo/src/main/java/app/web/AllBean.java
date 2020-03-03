package app.web;

import app.domain.models.service.CarServiceModel;
import app.service.CarService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AllBean {
    private List<CarServiceModel> cars;
    private CarService carService;

    public AllBean() {
    }

    @Inject
    public AllBean(CarService carService) {
        this.carService = carService;
    }

    @PostConstruct
    private void init() {
        cars = carService.getAllCars();
    }

    public List<CarServiceModel> getCars() {
        return cars;
    }

    public void setCars(List<CarServiceModel> cars) {
        this.cars = cars;
    }
}
