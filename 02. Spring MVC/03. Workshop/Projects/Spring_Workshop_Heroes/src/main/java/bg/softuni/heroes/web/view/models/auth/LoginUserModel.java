package bg.softuni.heroes.web.view.models.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserModel {
    private String username;
    private String password;
}
