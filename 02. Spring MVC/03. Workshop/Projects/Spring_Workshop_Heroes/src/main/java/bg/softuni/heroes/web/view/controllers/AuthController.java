package bg.softuni.heroes.web.view.controllers;

import bg.softuni.heroes.service.models.auth.LoginUserServiceModel;
import bg.softuni.heroes.service.models.auth.RegisterUserServiceModel;
import bg.softuni.heroes.service.services.AuthService;
import bg.softuni.heroes.web.view.models.auth.LoginUserModel;
import bg.softuni.heroes.web.view.models.auth.RegisterUserModel;
import bg.softuni.heroes.web.view.models.UserSessionModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class AuthController {
    private final AuthService authService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/login")
    public String getLoginForm() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginUserModel model, HttpSession session) {
        try {
            UserSessionModel user = this.modelMapper.map(
                    this.authService.login(this.modelMapper.map(model, LoginUserServiceModel.class)),
                    UserSessionModel.class
            );
            session.setAttribute("user", user);
            return "redirect:/home";
        } catch (Exception ex) {
            return "redirect:/users/login";
        }
    }

    @GetMapping("/register")
    public String getRegisterForm() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterUserModel user) {
        this.authService.register(this.modelMapper.map(user, RegisterUserServiceModel.class));
        return "redirect:/";
    }
}

