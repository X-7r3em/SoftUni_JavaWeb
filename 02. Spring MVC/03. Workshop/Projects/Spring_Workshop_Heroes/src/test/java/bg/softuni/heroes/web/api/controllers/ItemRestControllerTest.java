package bg.softuni.heroes.web.api.controllers;

import bg.softuni.heroes.data.models.Item;
import bg.softuni.heroes.data.models.Slot;
import bg.softuni.heroes.data.repositories.ItemRepository;
import bg.softuni.heroes.web.api.base.BaseRestTest;
import bg.softuni.heroes.web.view.models.items.ItemViewModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemRestControllerTest extends BaseRestTest {
    @MockBean
    ItemRepository itemRepository;

    @Test
    void getAll_whenItems_shouldReturnItems() {
        Item item = new Item();
        String itemName = "Sword";
        item.setId("1dasda");
        item.setName(itemName);
        Slot itemSlot = Slot.WEAPON;
        item.setSlot(itemSlot);
        Mockito.when(itemRepository.findAll()).thenReturn(List.of(item));

        var result = restTemplate.getForObject(getFullRoute("/api/items-test"), ItemViewModel[].class);

        assertEquals(result[0].getName(), itemName);
        assertEquals(result[0].getSlot(), itemSlot.name());
    }
}