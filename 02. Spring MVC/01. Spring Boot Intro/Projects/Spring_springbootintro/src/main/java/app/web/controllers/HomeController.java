package app.web.controllers;

import app.services.models.BeerServiceModel;
import app.web.models.BeerViewModel;
import app.web.models.CreateBeerViewModel;
import app.services.BeerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final BeerService beerService;
    private final ModelMapper mapper;

    public HomeController(BeerService beerService, ModelMapper mapper) {
        this.beerService = beerService;
        this.mapper = mapper;
    }

    @GetMapping("/text")
    @ResponseBody
    public String fromString() {
        return "It works from txt!";
    }

    @GetMapping("/html")
    public String fromHtml() {
        return "index.html";
    }

    @GetMapping("/")
    public ModelAndView fromHtml(ModelAndView modelAndView) {
        modelAndView.setViewName("home.html");
        modelAndView.addObject("text", "Hello from Model and view!");
        modelAndView.addObject("beers",
                this.beerService.getAll()
                        .stream()
                        .map(b -> this.mapper.map(b, BeerViewModel.class))
                        .collect(Collectors.toList()));
        return modelAndView;
    }

    @GetMapping("/new/{beerName}")
    public ModelAndView getBeerDetails(@PathVariable("beerName") String beerName, ModelAndView modelAndView) {
        modelAndView.setViewName("details.html");
        modelAndView.addObject("beerName", beerName);
        return modelAndView;
    }

    @PostMapping("/")
    public String createBeer(@ModelAttribute CreateBeerViewModel createBeerViewModel) {
        this.beerService.addBeer(this.mapper.map(createBeerViewModel, BeerServiceModel.class));
        return "redirect:/";
    }
}
