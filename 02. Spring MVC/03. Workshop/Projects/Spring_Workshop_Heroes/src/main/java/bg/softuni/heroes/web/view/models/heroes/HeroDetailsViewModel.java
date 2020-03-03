package bg.softuni.heroes.web.view.models.heroes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeroDetailsViewModel {
    private String name;
    private String gender;
    private int level;
    private int stamina;
    private int strength;
    private int attack;
    private int defence;
    private boolean hasWeapon;
    private boolean hasHelmet;
    private boolean hasPauldron;
    private boolean hasPads;
    private boolean hasGauntlets;
}

