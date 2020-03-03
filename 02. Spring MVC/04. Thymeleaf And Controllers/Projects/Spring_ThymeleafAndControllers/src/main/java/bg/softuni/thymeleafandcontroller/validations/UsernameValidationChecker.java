package bg.softuni.thymeleafandcontroller.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidationChecker implements ConstraintValidator<UsernameValidation, String> {
    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return username.length() > 3 && username.length() < 10;
    }
}
