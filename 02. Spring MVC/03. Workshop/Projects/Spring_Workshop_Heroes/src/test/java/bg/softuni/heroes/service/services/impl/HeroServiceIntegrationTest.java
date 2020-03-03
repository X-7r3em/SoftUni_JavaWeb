package bg.softuni.heroes.service.services.impl;

import bg.softuni.heroes.data.models.Gender;
import bg.softuni.heroes.data.models.Hero;
import bg.softuni.heroes.data.models.User;
import bg.softuni.heroes.data.repositories.HeroRepository;
import bg.softuni.heroes.data.repositories.UserRepository;
import bg.softuni.heroes.service.models.heroes.HeroCreateServiceModel;
import bg.softuni.heroes.service.services.HeroService;
import bg.softuni.heroes.service.services.base.BaseServiceTest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

class HeroServiceIntegrationTest extends BaseServiceTest {

    @MockBean
    HeroRepository heroRepository;

    @MockBean
    UserRepository userRepository;

    @Autowired
    HeroService service;

    @Test
    public void create_whenUserExistsAndDoesNotHaveAHero_shouldCreateHeroForUser() {
        User user = new User();
        String username = "Pesho";
        user.setUsername(username);
        Mockito.when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(user);
        HeroCreateServiceModel heroToCreate = new HeroCreateServiceModel("Gosho", Gender.FEMALE.name());
        ArgumentCaptor<Hero> heroArgument = ArgumentCaptor.forClass(Hero.class);

        service.create(user.getUsername(), heroToCreate);

        verify(heroRepository).saveAndFlush(heroArgument.capture());
        assertEquals(username, heroArgument.getValue().getUser().getUsername());
    }

    @Test
    public void create_whenUserDoesNotExist_shouldThrowException() {
        String username = "Pesho";
        Mockito.when(userRepository.findByUsername(username))
                .thenReturn(null);

        HeroCreateServiceModel heroToCreate = new HeroCreateServiceModel("Gosho", Gender.FEMALE.name());

        assertThrows(IllegalArgumentException.class, () -> {
            service.create(username, heroToCreate);
        });
    }

    @Test
    public void create_whenUserExistsAndDoesHaveAHero_shouldThrowException() {
        User user = new User();
        String username = "Pesho";
        user.setUsername(username);
        user.setHero(new Hero());
        Mockito.when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(user);

        HeroCreateServiceModel heroToCreate = new HeroCreateServiceModel("Gosho", Gender.FEMALE.name());

        assertThrows(IllegalArgumentException.class, () -> {
            service.create(user.getUsername(), heroToCreate);
        });
    }

    @Test
    public void levelUp_whenCalled_shouldIncreaseLevelOfHero() {
        Hero hero = new Hero() {{
           setStrength(1);
           setStamina(2);
           setAttack(3);
           setDefence(4);
           setLevel(1);
        }};

        service.levelUp(hero);

        assertEquals(2, hero.getLevel());
        assertEquals(6, hero.getStrength());
        assertEquals(7, hero.getStamina());
        assertEquals(3, hero.getAttack());
        assertEquals(4, hero.getDefence());
    }
}