package bg.softuni.heroes.service.services.impl;

import bg.softuni.heroes.data.models.*;
import bg.softuni.heroes.data.repositories.HeroRepository;
import bg.softuni.heroes.data.repositories.ItemRepository;
import bg.softuni.heroes.service.models.items.ItemServiceModel;
import bg.softuni.heroes.service.services.ItemService;
import bg.softuni.heroes.service.services.base.BaseServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemServiceIntegrationTest extends BaseServiceTest {
    List<Item> items;
    Hero hero;
    User user;

    @MockBean
    ItemRepository itemRepository;

    @MockBean
    HeroRepository heroRepository;

    @Autowired
    ItemService itemService;

    @BeforeEach
    public void setupTests() {
        MockitoAnnotations.initMocks(this);

        items = new ArrayList<>();
        user = new User();
        setupUser();
        hero = new Hero();
        setupHero();

        Mockito.when(itemRepository.findAll())
                .thenReturn(items);

        Mockito.when(heroRepository.findByName(hero.getName()))
                .thenReturn(Optional.of(hero));
    }

    @Test
    public void getAllAvailable_whenNoItem_shouldReturnNoItem() {
        items.clear();

        List<ItemServiceModel> actualItems = itemService.getAllAvailable(hero.getName());

        assertEquals(0, actualItems.size());
    }

    @Test
    public void getAllAvailable_whenNoAvailableItem_shouldReturnNoAvailableItem() {
        items.addAll(getHeroTestItems());
        hero.getItems().addAll(getHeroTestItems());

        List<ItemServiceModel> actualItems = itemService.getAllAvailable(hero.getName());

        assertEquals(items.size(), actualItems.size());

        Optional<ItemServiceModel> anyItem = actualItems.stream()
                .filter(ItemServiceModel::isFree)
                .findAny();

        assertTrue(anyItem.isEmpty());
    }

    @Test
    public void getAllAvailable_whenOneAvailableItem_shouldReturnOneAvailableItem() {
        items.addAll(getHeroTestItems());
        String id = "available";
        Item availableItem = new Item() {{
            setId(id);
            setName("availableItem");
            setSlot(Slot.GAUNTLETS);
            setAttack(1);
            setDefence(2);
            setStamina(3);
            setStrength(4);
        }};

        items.add(availableItem);
        hero.getItems().addAll(getHeroTestItems());

        List<ItemServiceModel> actualItems = itemService.getAllAvailable(hero.getName());

        assertEquals(items.size(), actualItems.size());

        List<ItemServiceModel> availableItems = actualItems.stream()
                .filter(ItemServiceModel::isFree)
                .collect(Collectors.toList());

        assertEquals(availableItems.size(), 1);
        assertEquals(availableItems.get(0).getId(), availableItem.getId());
    }

    private void setupUser() {
        user.setId("1");
        user.setUsername("user");
        user.setPassword("password");
        user.setEmail("Email");
    }

    public void setupHero() {
        hero.setId("2");
        hero.setName("HeroName");
        hero.setGender(Gender.FEMALE);
        hero.setStrength(4);
        hero.setStamina(3);
        hero.setAttack(1);
        hero.setDefence(2);
        hero.setItems(new ArrayList<>());
        hero.setUser(user);
    }

    private List<Item> getHeroTestItems() {
        return List.of(
                new Item() {{
                    setId("3");
                    setName("Item1");
                    setSlot(Slot.GAUNTLETS);
                    setAttack(1);
                    setDefence(2);
                    setStamina(3);
                    setStrength(4);
                }},
                new Item() {{
                    setId("4");
                    setName("Item2");
                    setSlot(Slot.PADS);
                    setAttack(10);
                    setDefence(20);
                    setStamina(30);
                    setStrength(40);
                }},
                new Item() {{
                    setId("5");
                    setName("Item3");
                    setSlot(Slot.WEAPON);
                    setAttack(5);
                    setDefence(7);
                    setStamina(12);
                    setStrength(15);
                }},
                new Item() {{
                    setId("6");
                    setName("Item4");
                    setSlot(Slot.HELMET);
                    setAttack(11);
                    setDefence(22);
                    setStamina(33);
                    setStrength(44);
                }},
                new Item() {{
                    setId("7");
                    setName("Item5");
                    setSlot(Slot.PAULDRON);
                    setAttack(111);
                    setDefence(222);
                    setStamina(333);
                    setStrength(444);
                }}
        );
    }
}