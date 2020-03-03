package bg.softuni.springessentials.web.controllers;

import bg.softuni.springessentials.service.models.CarServiceModel;
import bg.softuni.springessentials.service.services.CarService;
import bg.softuni.springessentials.web.models.CarBindingModel;
import bg.softuni.springessentials.web.models.CarViewModel;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/cars")
public class CarController {
    private final CarService carService;
    private final ModelMapper modelMapper;

    @Autowired
    public CarController(CarService carService, ModelMapper modelMapper) {
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("create");
    }

    @PostMapping("/create")
    public ModelAndView confirmCreate(@ModelAttribute CarBindingModel carBindingModel) {
        this.carService.save(this.modelMapper.map(carBindingModel, CarServiceModel.class));
        return new ModelAndView("redirect:/cars/all");
    }

    @GetMapping("/all")
    public ModelAndView all(ModelAndView modelAndView) {
        List<CarViewModel> cars = this.carService.getAll()
                .stream()
                .map(c -> this.modelMapper.map(c, CarViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("cars", cars);
        modelAndView.setViewName("all");
        return modelAndView;
    }
}
