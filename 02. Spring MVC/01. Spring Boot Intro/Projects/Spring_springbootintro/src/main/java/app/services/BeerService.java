package app.services;

import app.services.models.BeerServiceModel;

import java.util.List;

public interface BeerService {
    List<BeerServiceModel> getAll();

    void addBeer(BeerServiceModel beerServiceModel);
}
