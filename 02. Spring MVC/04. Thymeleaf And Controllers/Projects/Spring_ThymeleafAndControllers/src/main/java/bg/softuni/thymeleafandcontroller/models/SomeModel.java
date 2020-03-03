package bg.softuni.thymeleafandcontroller.models;

import bg.softuni.thymeleafandcontroller.validations.UsernameValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
public class SomeModel {
    @UsernameValidation(message = "Size of username is not correct!")
    private String name;
    @Min(value = 5, message = "Must be > 5") @Max(value = 10, message = "Must be < 10")
    private String age;
}
