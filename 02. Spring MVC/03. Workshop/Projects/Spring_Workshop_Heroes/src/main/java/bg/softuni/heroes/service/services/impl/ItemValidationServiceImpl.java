package bg.softuni.heroes.service.services.impl;

import bg.softuni.heroes.service.models.items.CreateItemServiceModel;
import bg.softuni.heroes.service.services.ItemValidationService;
import org.springframework.stereotype.Service;

@Service
public class ItemValidationServiceImpl implements ItemValidationService {
    @Override
    public boolean isValid(CreateItemServiceModel model) {
        return model != null
                && model.getName() != null
                && model.getSlot() != null
                && model.getAttack() >= 0
                && model.getDefence() >= 0
                && model.getStrength() >= 0
                && model.getStamina() >= 0;
    }
}
