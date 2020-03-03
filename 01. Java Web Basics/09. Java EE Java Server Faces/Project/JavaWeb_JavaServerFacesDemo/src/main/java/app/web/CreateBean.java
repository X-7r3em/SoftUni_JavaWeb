package app.web;

import app.domain.models.service.CarServiceModel;
import app.service.CarService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class CreateBean {
    private CarService carService;
    private CarServiceModel car;

    public CreateBean() {
    }

    @Inject
    public CreateBean(CarService carService) {
        this.carService = carService;
    }

    @PostConstruct
    private void init() {
        this.car = new CarServiceModel();
    }

    public void createCar() throws IOException {
        carService.saveCar(car);

        FacesContext.getCurrentInstance()
                .getExternalContext().redirect("/all");
    }

    public CarServiceModel getCar() {
        return car;
    }

    public void setCar(CarServiceModel car) {
        this.car = car;
    }
}
