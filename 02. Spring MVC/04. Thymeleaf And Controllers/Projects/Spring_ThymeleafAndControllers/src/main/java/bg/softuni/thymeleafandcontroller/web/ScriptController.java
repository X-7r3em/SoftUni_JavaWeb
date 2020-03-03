package bg.softuni.thymeleafandcontroller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScriptController {
    @GetMapping("/script")
    public String getScript(Model model) {
        model.addAttribute("message", "Hello JavaScript!");
        return "script";
    }
}
