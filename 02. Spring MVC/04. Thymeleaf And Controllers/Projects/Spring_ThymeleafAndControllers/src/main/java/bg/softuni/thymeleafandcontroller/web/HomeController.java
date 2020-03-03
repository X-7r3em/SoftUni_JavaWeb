package bg.softuni.thymeleafandcontroller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Date;

@Controller
public class HomeController {


    @GetMapping({"/", "home"})
    public String getHome(Model model) {
        model.addAttribute("date", new Date());
        model.addAttribute("localDate", LocalDate.of(1, 2, 3));
        model.addAttribute("text", "012345");
        model.addAttribute("allUpperCase", "HELLO UPPERCASE");
        return "home";
    }
}
