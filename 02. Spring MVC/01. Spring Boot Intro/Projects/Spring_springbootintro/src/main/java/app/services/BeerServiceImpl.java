package app.services;

import app.data.models.Beer;
import app.services.models.BeerServiceModel;
import app.data.repositories.BeerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final ModelMapper mapper;

    public BeerServiceImpl(ModelMapper mapper, BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<BeerServiceModel> getAll() {
        return this.beerRepository.findAll().stream()
                .map(b -> this.mapper.map(b, BeerServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addBeer(BeerServiceModel beerServiceModel) {
        this.beerRepository.saveAndFlush(this.mapper.map(beerServiceModel, Beer.class));
    }
}
