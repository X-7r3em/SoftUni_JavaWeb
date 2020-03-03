package bg.softuni.thymeleafandcontroller.web;

import bg.softuni.thymeleafandcontroller.models.SomeModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ValidateTestController {
    @ModelAttribute("someModel")
    public SomeModel someModel() {
        SomeModel model = new SomeModel();
        model.setAge("17");
        model.setName("Vasko");
        return model;
    }

    @GetMapping("/validate")
    public String getValidate() {
        return "validate";
    }

    @PostMapping("/validate")
    public String postValidate(@Valid @ModelAttribute SomeModel someModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return "validate";
        }
        return "redirect:/validate";
    }
}
