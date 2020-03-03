package bg.softuni.heroes.web.view.controllers;

import bg.softuni.heroes.errors.NoSuchHeroException;
import bg.softuni.heroes.service.services.HeroService;
import bg.softuni.heroes.web.view.models.UserSessionModel;
import bg.softuni.heroes.web.view.models.heroes.HeroDetailsViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final HeroService heroService;
    private final ModelMapper modelMapper;

    public HomeController(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String getIndex() {
        return "home/index";
    }

    @GetMapping("/home")
    public ModelAndView getIndex(ModelAndView modelAndView, HttpSession session) {
        String heroName = ((UserSessionModel) session.getAttribute("user")).getHeroName();
        HeroDetailsViewModel hero;
        try {
            hero = this.modelMapper.map(this.heroService.getDetails(heroName), HeroDetailsViewModel.class);
        } catch (NoSuchHeroException ex) {
            hero = null;
        }
        List<HeroDetailsViewModel> opponents = this.heroService.getHeroOpponents(heroName)
                .stream()
                .map(h -> this.modelMapper.map(h, HeroDetailsViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("hero", hero);
        modelAndView.addObject("otherHeroes", opponents);
        modelAndView.setViewName("home/home");
        return modelAndView;
    }
}
