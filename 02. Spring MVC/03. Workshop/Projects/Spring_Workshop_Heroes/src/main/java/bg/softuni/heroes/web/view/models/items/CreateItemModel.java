package bg.softuni.heroes.web.view.models.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateItemModel {
    private String name;
    private String slot;
    private int stamina;
    private int strength;
    private int attack;
    private int defence;
}
