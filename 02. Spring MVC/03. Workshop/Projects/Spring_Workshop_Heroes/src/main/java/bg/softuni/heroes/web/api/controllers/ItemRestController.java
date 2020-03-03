package bg.softuni.heroes.web.api.controllers;

import bg.softuni.heroes.service.services.ItemService;
import bg.softuni.heroes.web.view.models.UserSessionModel;
import bg.softuni.heroes.web.view.models.items.ItemViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ItemRestController {
    private final ItemService itemService;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemRestController(ItemService itemService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/items")
    public List<ItemViewModel> getItems(HttpSession session) throws InterruptedException {
        UserSessionModel user = (UserSessionModel) session.getAttribute("user");
        return this.itemService.getAllAvailable(user.getHeroName())
                .stream()
                .map(i -> this.modelMapper.map(i, ItemViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/items-test")
    public List<ItemViewModel> getItemsTest() throws InterruptedException {
        return this.itemService.getAll()
                .stream()
                .map(i -> this.modelMapper.map(i, ItemViewModel.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/api/items/{id}")
    public ResponseEntity<Void> buyItems(@PathVariable String id, HttpSession session) throws IOException {
        this.itemService.buy(((UserSessionModel) session.getAttribute("user")).getHeroName(), id);
        return new ResponseEntity<>(HttpStatus.FOUND);
    }
}
