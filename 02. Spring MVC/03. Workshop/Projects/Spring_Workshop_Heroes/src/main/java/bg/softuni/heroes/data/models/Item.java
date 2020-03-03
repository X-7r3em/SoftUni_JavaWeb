package bg.softuni.heroes.data.models;

import bg.softuni.heroes.data.models.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "slot", nullable = false)
    @Enumerated(EnumType.STRING)
    private Slot slot;

    @Column(name = "stamina", nullable = false)
    private int stamina;

    @Column(name = "strength", nullable = false)
    private int strength;

    @Column(name = "attack", nullable = false)
    private int attack;

    @Column(name = "defence", nullable = false)
    private int defence;

    @ManyToMany(mappedBy = "items", targetEntity = Hero.class)
    private List<Hero> heroes;
}
