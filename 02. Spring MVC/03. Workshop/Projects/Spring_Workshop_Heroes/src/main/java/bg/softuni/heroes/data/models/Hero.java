package bg.softuni.heroes.data.models;

import bg.softuni.heroes.data.models.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "heroes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hero extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "level", nullable = false)
    private int level;

    @Column(name = "stamina", nullable = false)
    private int stamina;

    @Column(name = "strength", nullable = false)
    private int strength;

    @Column(name = "attack", nullable = false)
    private int attack;

    @Column(name = "defence", nullable = false)
    private int defence;

    @ManyToMany
    @JoinTable(name = "heroes_items",
        joinColumns = @JoinColumn(name = "hero_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"))
    private List<Item> items;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id")
    private User user;

}
