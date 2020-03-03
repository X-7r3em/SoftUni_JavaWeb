package bg.softuni.heroes.service.services;

import bg.softuni.heroes.service.models.items.CreateItemServiceModel;

public interface ItemValidationService {
    boolean isValid(CreateItemServiceModel model);
}
