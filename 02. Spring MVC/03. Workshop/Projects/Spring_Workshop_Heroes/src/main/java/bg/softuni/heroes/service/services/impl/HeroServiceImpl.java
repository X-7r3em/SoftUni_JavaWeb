package bg.softuni.heroes.service.services.impl;

import bg.softuni.heroes.data.models.Hero;
import bg.softuni.heroes.data.models.User;
import bg.softuni.heroes.data.repositories.HeroRepository;
import bg.softuni.heroes.data.repositories.UserRepository;
import bg.softuni.heroes.service.models.heroes.HeroCreateServiceModel;
import bg.softuni.heroes.service.models.heroes.HeroDetailsServiceModel;
import bg.softuni.heroes.service.models.heroes.HeroProfileDetailsServiceModel;
import bg.softuni.heroes.service.services.HeroService;
import bg.softuni.heroes.web.view.models.heroes.HeroDetailsViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static bg.softuni.heroes.constants.ExceptionConstants.NO_SUCH_HERO_EXCEPTION;

@Service
public class HeroServiceImpl implements HeroService {
    private final HeroRepository heroRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HeroServiceImpl(HeroRepository heroRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.heroRepository = heroRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(String username, HeroCreateServiceModel model) {
        Hero hero = this.modelMapper.map(model, Hero.class);
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("No such user");
        }

        if (user.getHero() != null) {
            throw new IllegalArgumentException("User already has a hero");
        }

        hero.setUser(user);
        this.heroRepository.saveAndFlush(hero);
    }

    @Override
    public HeroDetailsServiceModel getDetails(String name) {
        return this.getDetailsByType(name, HeroDetailsServiceModel.class);
    }

    @Override
    public HeroProfileDetailsServiceModel getProfileDetails(String name) {
        return this.getDetailsByType(name, HeroProfileDetailsServiceModel.class);
    }

    @Override
    public List<HeroProfileDetailsServiceModel> getHeroOpponents(String currentHero) {
        return this.heroRepository.findAll()
                .stream()
                .filter(h -> !h.getName().equals(currentHero))
                .map(h -> this.modelMapper.map(h, HeroProfileDetailsServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public String fight(String heroName, String opponentName) {
        Hero hero = this.heroRepository.findByName(heroName).orElseThrow(NO_SUCH_HERO_EXCEPTION);
        Hero opponent = this.heroRepository.findByName(opponentName).orElseThrow(NO_SUCH_HERO_EXCEPTION);
        int heroDamage = this.calculateDamage(hero, opponent);
        int opponentDamage = this.calculateDamage(opponent, hero);
        String winnerName = null;
        if (heroDamage >= opponentDamage) {
            winnerName = heroName;
            levelUp(hero);
            this.heroRepository.saveAndFlush(hero);
        } else {
            winnerName = opponentName;
            levelUp(opponent);
            this.heroRepository.saveAndFlush(opponent);
        }

        return winnerName;
    }

    private int calculateDamage(Hero hero, Hero opponent) {
        return hero.getAttack() + hero.getStrength() * 4 - (opponent.getDefence() + opponent.getStrength() * 2);
    }

    @Override
    public void levelUp(Hero hero) {
        hero.setLevel(hero.getLevel() + 1);
        hero.setStrength(hero.getStrength() + 5);
        hero.setStamina(hero.getStamina() + 5);
    }

    private <T> T getDetailsByType(String name, Class<T> modelClass) {
        Hero hero = this.heroRepository.findByName(name).orElseThrow(NO_SUCH_HERO_EXCEPTION);
        return this.modelMapper.map(hero, modelClass);
    }
}
