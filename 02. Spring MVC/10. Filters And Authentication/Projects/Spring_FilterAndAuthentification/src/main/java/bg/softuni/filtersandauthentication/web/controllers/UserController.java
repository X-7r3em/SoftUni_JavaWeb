package bg.softuni.filtersandauthentication.web.controllers;

import bg.softuni.filtersandauthentication.service.models.UserServiceModel;
import bg.softuni.filtersandauthentication.service.services.UserService;
import bg.softuni.filtersandauthentication.web.models.UserRegisterModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    private String register() {
        return "register";
    }

    @PostMapping("/register")
    private String registerConfirm(@ModelAttribute UserRegisterModel model) {
        if (!model.getConfirmPassword().equals(model.getPassword())) {
            return "register";
        }

        userService.register(modelMapper.map(model, UserServiceModel.class));
        return "redirect:home";
    }

    @GetMapping("/login")
    private String login(@RequestParam(required = false) String error) {
        return "login";
    }
}
