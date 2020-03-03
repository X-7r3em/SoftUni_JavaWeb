package app.repository;

import app.domain.entity.Hero;

import java.util.List;

public interface HeroRepository {
    void save(Hero hero);

    void delete(String id);

    Hero getById(String id);

    Hero getByName(String name);

    List<Hero> getAll();
}
