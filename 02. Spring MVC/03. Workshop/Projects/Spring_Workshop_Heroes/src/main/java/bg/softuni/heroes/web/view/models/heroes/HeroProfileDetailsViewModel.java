package bg.softuni.heroes.web.view.models.heroes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeroProfileDetailsViewModel {
    private String name;
    private String level;
    private String gender;
}

