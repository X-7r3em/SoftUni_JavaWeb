package app.service;

import app.domain.model.service.HeroServiceModel;

import java.util.List;

public interface HeroService {
    void save(HeroServiceModel hero);

    void delete(String id);

    HeroServiceModel getById(String id);

    HeroServiceModel getByName(String name);

    List<HeroServiceModel> getAll();
}
