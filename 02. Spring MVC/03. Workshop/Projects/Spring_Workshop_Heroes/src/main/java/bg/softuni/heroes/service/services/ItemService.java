package bg.softuni.heroes.service.services;

import bg.softuni.heroes.service.models.items.ItemServiceModel;
import bg.softuni.heroes.service.models.items.CreateItemServiceModel;
import bg.softuni.heroes.service.models.items.UserBuyItemServiceModel;

import java.util.Arrays;
import java.util.List;

public interface ItemService {
    void create(CreateItemServiceModel model);

    List<ItemServiceModel> getAllAvailable(String username);

    void buy(String heroName, String itemId);

    List<ItemServiceModel> getAll();
}
