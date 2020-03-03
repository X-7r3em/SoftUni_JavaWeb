package bg.softuni.heroes.web.view.controllers;

import bg.softuni.heroes.service.services.HeroService;
import bg.softuni.heroes.service.services.UserService;
import bg.softuni.heroes.web.view.models.UserSessionModel;
import bg.softuni.heroes.web.view.models.heroes.HeroProfileDetailsViewModel;
import bg.softuni.heroes.web.view.models.users.UserProfileViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;
    private final HeroService heroService;
    private final ModelMapper modelMapper;

    @Autowired
    public UsersController(UserService userService, HeroService heroService, ModelMapper modelMapper) {
        this.userService = userService;
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/profile")
    public ModelAndView getUserProfile(ModelAndView modelAndView, HttpSession session) {
        UserSessionModel user = (UserSessionModel) session.getAttribute("user");
        UserProfileViewModel userDetails = this.modelMapper.map(
                this.userService.getUserShortDetails(user.getUsername()), UserProfileViewModel.class
        );
        HeroProfileDetailsViewModel heroDetails = this.modelMapper.map(
                this.heroService.getProfileDetails(user.getHeroName()), HeroProfileDetailsViewModel.class
        );
        modelAndView.addObject("user", userDetails);
        modelAndView.addObject("hero", heroDetails);
        modelAndView.setViewName("users/profile");
        return modelAndView;
    }
}
