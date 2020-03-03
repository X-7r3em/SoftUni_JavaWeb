package bg.softuni.heroes.service.models.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserServiceModel {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}
