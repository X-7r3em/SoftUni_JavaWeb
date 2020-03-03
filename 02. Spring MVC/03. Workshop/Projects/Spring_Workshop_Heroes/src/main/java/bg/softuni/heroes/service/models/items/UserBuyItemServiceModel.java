package bg.softuni.heroes.service.models.items;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserBuyItemServiceModel {
    private String heroName;
    private String itemId;
}