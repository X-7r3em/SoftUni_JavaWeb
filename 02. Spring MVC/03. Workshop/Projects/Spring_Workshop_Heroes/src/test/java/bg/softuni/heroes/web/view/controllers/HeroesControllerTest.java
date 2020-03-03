package bg.softuni.heroes.web.view.controllers;

import bg.softuni.heroes.data.models.Gender;
import bg.softuni.heroes.data.models.Hero;
import bg.softuni.heroes.data.repositories.HeroRepository;
import bg.softuni.heroes.base.BaseTest;
import bg.softuni.heroes.web.view.base.BaseViewTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static bg.softuni.heroes.web.view.controllers.HeroesController.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class HeroesControllerTest extends BaseViewTest {
    @MockBean
    HeroRepository mockHeroRepository;

    @Test
    void getDetails_whenNoHeroWithName_shouldReturnErrorViewPageWith404() throws Exception {
        String heroName = "Pesho";
        Mockito.when(mockHeroRepository.findByName(heroName))
                .thenReturn(Optional.empty());
        mockMvc.perform(get("/heroes/details/" + heroName))
                .andExpect(status().isNotFound())
                .andExpect(view().name(HEROES_LOCAL_ERROR_VIEW_NAME));
    }

    @Test
    void getDetails_whenHeroWithName_shouldReturnHeroDetailsViewPageWith200() throws Exception {
        String heroName = "Pesho";
        Hero hero = new Hero();
        hero.setName(heroName);
        hero.setGender(Gender.FEMALE);
        hero.setItems(new ArrayList<>());

        Mockito.when(mockHeroRepository.findByName(heroName))
                .thenReturn(Optional.of(hero));

        mockMvc.perform(get("/heroes/details/" + heroName))
                .andExpect(status().isOk())
                .andExpect(view().name(HEROES_HERO_DETAIL_VIEW_NAME));
    }
}