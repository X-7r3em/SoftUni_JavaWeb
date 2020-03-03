package bg.softuni.thymeleafandcontroller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NumbersController {
    @GetMapping("/numbers")
    public String getNumbers(Model model) {
        model.addAttribute("num", 3.14141);
        model.addAttribute("numbers", new int[] {1, 2, 3});
        return "numbers";
    }
}
