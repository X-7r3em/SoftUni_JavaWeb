package app.service;

import app.domain.entity.Hero;
import app.domain.model.service.HeroServiceModel;
import app.repository.HeroRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HeroServiceImpl implements HeroService {
    private HeroRepository heroRepository;
    private ModelMapper modelMapper;

    @Inject
    public HeroServiceImpl(HeroRepository heroRepository, ModelMapper modelMapper) {
        this.heroRepository = heroRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(HeroServiceModel hero) {
        this.heroRepository.save(this.modelMapper.map(hero, Hero.class));
    }

    @Override
    public void delete(String id) {
        this.heroRepository.delete(id);
    }

    @Override
    public HeroServiceModel getById(String id) {
        return this.modelMapper.map(this.heroRepository.getById(id), HeroServiceModel.class);
    }

    @Override
    public HeroServiceModel getByName(String name) {
        Hero heroDb = this.heroRepository.getByName(name);
        if (heroDb == null) {
            return null;
        }
        return this.modelMapper.map(heroDb, HeroServiceModel.class);
    }

    @Override
    public List<HeroServiceModel> getAll() {
        return this.heroRepository.getAll()
                .stream()
                .map(h -> this.modelMapper.map(h, HeroServiceModel.class))
                .sorted(Comparator.comparing(HeroServiceModel::getLevel).reversed())
                .collect(Collectors.toList());
    }
}
