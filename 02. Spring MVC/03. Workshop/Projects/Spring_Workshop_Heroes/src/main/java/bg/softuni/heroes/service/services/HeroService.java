package bg.softuni.heroes.service.services;

import bg.softuni.heroes.data.models.Hero;
import bg.softuni.heroes.service.models.heroes.HeroCreateServiceModel;
import bg.softuni.heroes.service.models.heroes.HeroDetailsServiceModel;
import bg.softuni.heroes.service.models.heroes.HeroProfileDetailsServiceModel;

import java.util.List;

public interface HeroService {
    void create(String username, HeroCreateServiceModel model);

    HeroDetailsServiceModel getDetails(String name);

    HeroProfileDetailsServiceModel getProfileDetails(String name);

    List<HeroProfileDetailsServiceModel> getHeroOpponents(String currentHero);

    String fight(String heroName, String opponentName);

    void levelUp(Hero hero);
}
