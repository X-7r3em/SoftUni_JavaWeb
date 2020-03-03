package bg.softuni.errorhandling.web.controllers;

import bg.softuni.errorhandling.web.models.HomeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public class BaseController {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleDbExceptions(HomeException e) {
        ModelAndView modelAndView = new ModelAndView("local-error");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }
}
