package bg.softuni.heroes.web.view.controllers;

import bg.softuni.heroes.errors.NoSuchHeroException;
import bg.softuni.heroes.service.models.heroes.HeroCreateServiceModel;
import bg.softuni.heroes.service.services.HeroService;
import bg.softuni.heroes.web.view.models.heroes.HeroCreateModel;
import bg.softuni.heroes.web.view.models.UserSessionModel;
import bg.softuni.heroes.web.view.models.heroes.HeroDetailsViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/heroes")
public class HeroesController {
    public static final String HEROES_HERO_DETAIL_VIEW_NAME = "heroes/hero-details";
    public static final String HEROES_LOCAL_ERROR_VIEW_NAME = "local-error";

    private final HeroService heroService;
    private final ModelMapper modelMapper;

    @Autowired
    public HeroesController(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public String getHeroCreateForm() {
        return "heroes/create-hero";
    }

    @PostMapping("/create")
    public String createHero(@ModelAttribute HeroCreateModel model, HttpSession session) {
        UserSessionModel user = (UserSessionModel) session.getAttribute("user");
        HeroCreateServiceModel heroServiceModel = this.modelMapper.map(model, HeroCreateServiceModel.class);
        user.setHeroName(heroServiceModel.getName());
        this.heroService.create(user.getUsername(), heroServiceModel);
        return "redirect:/heroes/details/" + heroServiceModel.getName();
    }

    @GetMapping("/fight/{opponentName}")
    public ModelAndView getFightResult(@PathVariable String opponentName, HttpSession session, ModelAndView modelAndView) {
        String heroName = ((UserSessionModel) session.getAttribute("user")).getHeroName();
        HeroDetailsViewModel hero = this.modelMapper.map(this.heroService.getProfileDetails(heroName), HeroDetailsViewModel.class);
        HeroDetailsViewModel opponent = this.modelMapper.map(this.heroService.getProfileDetails(opponentName), HeroDetailsViewModel.class);
        String winner = heroService.fight(heroName, opponentName);
        modelAndView.setViewName("heroes/hero-fight");
        modelAndView.addObject("hero", hero);
        modelAndView.addObject("opponent", opponent);
        modelAndView.addObject("winner", winner);
        return modelAndView;
    }

    @GetMapping("/details/{name}")
    public ModelAndView getHeroDetails(@PathVariable String name, ModelAndView modelAndView) {
        HeroDetailsViewModel hero = this.modelMapper.map(this.heroService.getDetails(name), HeroDetailsViewModel.class);
        modelAndView.setViewName(HEROES_HERO_DETAIL_VIEW_NAME);
        modelAndView.addObject("hero", hero);
        return modelAndView;
    }

    @ExceptionHandler(NoSuchHeroException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleLocalException(NoSuchHeroException e) {
        ModelAndView modelAndView = new ModelAndView(HEROES_LOCAL_ERROR_VIEW_NAME);
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }
}
