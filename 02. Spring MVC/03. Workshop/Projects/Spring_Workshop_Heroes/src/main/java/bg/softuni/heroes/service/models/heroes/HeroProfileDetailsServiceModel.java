package bg.softuni.heroes.service.models.heroes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeroProfileDetailsServiceModel {
    private String name;
    private String level;
    private String gender;
}
