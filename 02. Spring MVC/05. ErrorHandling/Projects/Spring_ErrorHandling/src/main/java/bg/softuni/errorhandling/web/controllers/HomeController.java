package bg.softuni.errorhandling.web.controllers;

import bg.softuni.errorhandling.web.models.HomeException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends BaseController {

    @GetMapping("/home")
    public String getHome() throws HomeException {
        throw new HomeException("Thrown Home Exception!");
    }
}
