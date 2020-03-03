package bg.softuni.filtersandauthentication.web.controllers;

import bg.softuni.filtersandauthentication.web.models.FormCommand;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView home(@ModelAttribute("command") FormCommand command, ModelAndView modelAndView) {
        modelAndView.setViewName("home");
        modelAndView.addObject("allValues", getMultiCheckboxAllValues());
        return modelAndView;
    }

    private String[] getMultiCheckboxAllValues() {
        return new String[]{
                "Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday", "Saturday", "Sunday"
        };
    }

    @PostMapping("/home")
    public ModelAndView submitHome(@ModelAttribute("command") FormCommand command, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/restricted")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String restricted() {
        return "restricted";
    }

    @GetMapping("/restrictedConfig")
    public String restrictedWithConfig() {
        return "restrictedConfig";
    }

    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }
}
