package app.web;

import app.domain.model.view.HeroViewModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class HeroDetailBean extends BaseBean {
    private HeroViewModel hero;

    private HeroService heroService;
    private ModelMapper modelMapper;

    public HeroDetailBean() {
    }

    @Inject
    public HeroDetailBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        String id = this.getHeroId();
        this.hero = this.modelMapper.map(this.heroService.getById(id), HeroViewModel.class);
    }

    public HeroViewModel getHero() {
        return hero;
    }

    public void setHero(HeroViewModel hero) {
        this.hero = hero;
    }
}
