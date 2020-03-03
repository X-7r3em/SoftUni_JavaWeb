package bg.softuni.heroes.web.view.models.heroes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeroCreateModel {
    private String name;
    private String gender;
}
