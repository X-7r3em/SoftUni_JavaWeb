package bg.softuni.heroes.web.view.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException() {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", "Ups, there was an error!");
        return modelAndView;
    }
}
