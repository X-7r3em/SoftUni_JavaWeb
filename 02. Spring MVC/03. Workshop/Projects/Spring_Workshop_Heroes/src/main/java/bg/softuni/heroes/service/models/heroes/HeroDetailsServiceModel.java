package bg.softuni.heroes.service.models.heroes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeroDetailsServiceModel {
    private String name;
    private String gender;
    private Integer level;
    private Integer stamina;
    private Integer strength;
    private Integer attack;
    private Integer defence;
    private Boolean hasWeapon;
    private Boolean hasHelmet;
    private Boolean hasPauldron;
    private Boolean hasPads;
    private Boolean hasGauntlets;
}
