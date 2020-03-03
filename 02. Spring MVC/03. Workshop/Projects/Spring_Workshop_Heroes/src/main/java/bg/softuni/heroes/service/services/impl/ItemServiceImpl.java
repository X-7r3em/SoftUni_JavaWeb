package bg.softuni.heroes.service.services.impl;

import bg.softuni.heroes.data.models.Hero;
import bg.softuni.heroes.data.models.Item;
import bg.softuni.heroes.data.models.Slot;
import bg.softuni.heroes.data.repositories.HeroRepository;
import bg.softuni.heroes.data.repositories.ItemRepository;
import bg.softuni.heroes.service.models.items.ItemServiceModel;
import bg.softuni.heroes.service.models.items.CreateItemServiceModel;
import bg.softuni.heroes.service.services.ItemService;
import bg.softuni.heroes.service.services.ItemValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static bg.softuni.heroes.constants.ExceptionConstants.*;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final HeroRepository heroRepository;
    private final ItemValidationService itemValidationService;
    private final ModelMapper modelMapper;

    public ItemServiceImpl(ItemRepository itemRepository, HeroRepository heroRepository, ItemValidationService itemValidationService, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.heroRepository = heroRepository;
        this.itemValidationService = itemValidationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(CreateItemServiceModel model) {
        if (!this.itemValidationService.isValid(model)) {
            throw new IllegalArgumentException("Invalid item!");
        }
        this.itemRepository.save(this.modelMapper.map(model, Item.class));
    }

    @Override
    public List<ItemServiceModel> getAllAvailable(String heroName) {
        Hero hero = this.heroRepository.findByName(heroName).orElseThrow(NO_SUCH_HERO_EXCEPTION);
        Set<String> currentItemNames = hero.getItems()
                .stream()
                .map(Item::getName)
                .collect(Collectors.toSet());
        List<Item> test = this.itemRepository.findAll();
        List<ItemServiceModel> items = this.itemRepository.findAll()
                .stream()
                .map(i -> this.modelMapper.map(i, ItemServiceModel.class))
                .collect(Collectors.toList());
        items.forEach(i -> i.setFree(!currentItemNames.contains(i.getName())));
        return items;
    }

    @Override
    public void buy(String heroName, String itemId) {
        Hero hero = this.heroRepository.findByName(heroName).orElseThrow(NO_SUCH_HERO_EXCEPTION);
        Optional<Item> newItemOptional = this.itemRepository.findById(itemId);
        if (newItemOptional.isEmpty()) {
            throw new NoSuchElementException("No such item");
        }

        Item newItem = newItemOptional.get();
        Slot itemSlot = newItem.getSlot();
        Item currentItem = hero.getItems().stream()
                .filter(i -> i.getSlot() == itemSlot)
                .findFirst().orElse(null);

        List<Item> currentItems = hero.getItems();
        currentItems.remove(currentItem);
        currentItems.add(newItem);

        this.updateHeroStats(hero, currentItem, newItem);

        this.heroRepository.saveAndFlush(hero);
    }

    @Override
    public List<ItemServiceModel> getAll() {
        return this.itemRepository.findAll()
                .stream()
                .map(i -> this.modelMapper.map(i, ItemServiceModel.class))
                .collect(Collectors.toList());
    }

    private void updateHeroStats(Hero hero, Item currentItem, Item newItem) {
        if (currentItem != null) {
            hero.setStrength(hero.getStrength() - currentItem.getStrength());
            hero.setStamina(hero.getStamina() - currentItem.getStamina());
            hero.setAttack(hero.getAttack() - currentItem.getAttack());
            hero.setDefence(hero.getDefence() - currentItem.getDefence());
        }

        hero.setStrength(hero.getStrength() + newItem.getStrength());
        hero.setStamina(hero.getStamina() + newItem.getStamina());
        hero.setAttack(hero.getAttack() + newItem.getAttack());
        hero.setDefence(hero.getDefence() + newItem.getDefence());
    }
}
