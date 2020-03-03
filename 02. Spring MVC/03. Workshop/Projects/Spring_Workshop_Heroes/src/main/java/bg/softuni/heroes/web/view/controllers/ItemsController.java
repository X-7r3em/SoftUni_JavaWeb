package bg.softuni.heroes.web.view.controllers;

import bg.softuni.heroes.service.models.items.CreateItemServiceModel;
import bg.softuni.heroes.service.services.ItemService;
import bg.softuni.heroes.web.view.models.UserSessionModel;
import bg.softuni.heroes.web.view.models.items.CreateItemModel;
import bg.softuni.heroes.web.view.models.items.ItemViewModel;
import bg.softuni.heroes.web.view.models.items.UserBuyItemModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/items")
public class ItemsController {
    private final ItemService itemService;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemsController(ItemService itemService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public String getCreateItemForm() {
        return "items/create-item";
    }

    @PostMapping("/create")
    public String createItem(@ModelAttribute CreateItemModel model) {
        this.itemService.create(this.modelMapper.map(model, CreateItemServiceModel.class));
        return "redirect:/items/merchant";
    }

    @GetMapping("/merchant")
    public ModelAndView getMerchant(ModelAndView modelAndView, HttpSession session) {
        UserSessionModel user = (UserSessionModel) session.getAttribute("user");
        List<ItemViewModel> items = this.itemService.getAllAvailable(user.getHeroName())
                .stream()
                .map(i -> this.modelMapper.map(i, ItemViewModel.class))
                .collect(Collectors.toList());
        modelAndView.setViewName("items/merchant");
        modelAndView.addObject("items", items);
        return modelAndView;
    }

    @PostMapping("/merchant")
    public String buyItem(@ModelAttribute UserBuyItemModel model, HttpSession session) {
        this.itemService.buy(((UserSessionModel) session.getAttribute("user")).getHeroName(),model.getItemId());
        return "redirect:/items/merchant";
    }

    @GetMapping("/merchant-async")
    public ModelAndView getMerchantAsync(ModelAndView modelAndView) {
        modelAndView.setViewName("items/merchant-async");
        return modelAndView;
    }
}
