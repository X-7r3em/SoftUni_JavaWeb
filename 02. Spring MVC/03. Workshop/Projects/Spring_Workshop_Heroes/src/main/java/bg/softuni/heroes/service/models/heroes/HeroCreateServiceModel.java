package bg.softuni.heroes.service.models.heroes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeroCreateServiceModel {
    private String name;
    private String gender;
}
