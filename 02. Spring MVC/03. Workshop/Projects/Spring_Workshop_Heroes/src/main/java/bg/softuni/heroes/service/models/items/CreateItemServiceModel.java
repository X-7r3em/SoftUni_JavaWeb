package bg.softuni.heroes.service.models.items;


import bg.softuni.heroes.data.models.Slot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateItemServiceModel {
    private String name;
    private Slot slot;
    private int stamina;
    private int strength;
    private int attack;
    private int defence;
    private String heroName;
}
