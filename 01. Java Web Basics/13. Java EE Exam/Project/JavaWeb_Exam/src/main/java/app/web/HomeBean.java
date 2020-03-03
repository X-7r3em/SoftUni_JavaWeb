package app.web;

import app.domain.model.view.HeroViewModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean extends BaseBean {
    private List<HeroViewModel> heroes;

    private HeroService heroService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.heroes = this.heroService.getAll()
                .stream()
                .map(h -> this.modelMapper.map(h, HeroViewModel.class))
                .collect(Collectors.toList());
    }

    public List<HeroViewModel> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<HeroViewModel> heroes) {
        this.heroes = heroes;
    }
}
