package bg.softuni.errorhandling.web.controllers;

import bg.softuni.errorhandling.web.models.ErrorModel;
import bg.softuni.errorhandling.web.models.RestException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RestExceptionController {
    @GetMapping("/rest")
    public String getRest() throws RestException {
        throw new RestException("Throw Rest Exception");
    }

    @ExceptionHandler(RestException.class)
    @ResponseBody
    public ErrorModel handleRestError(HttpServletRequest req, RestException e) {
        return new ErrorModel(req.getRequestURL().toString(), e.getMessage());
    }
}
