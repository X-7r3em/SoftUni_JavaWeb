package bg.softuni.errorhandling.web.controllers;

import bg.softuni.errorhandling.web.models.InfoException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {
    @GetMapping("/info")
    public String getInfo() throws InfoException {
        throw new InfoException("Thrown Info Exception");
    }
}
