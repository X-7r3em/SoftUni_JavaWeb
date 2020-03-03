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
public class HeroDeleteBean extends BaseBean {
    private HeroViewModel hero;

    private HeroService heroService;
    private ModelMapper modelMapper;

    public HeroDeleteBean() {
    }

    @Inject
    public HeroDeleteBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        String id = this.getHeroId();
        this.hero = this.modelMapper.map(this.heroService.getById(id), HeroViewModel.class);
    }

    public void delete() {
        String id = this.getHeroId();
        this.heroService.delete(id);
        this.redirect("/home");
    }

    public HeroViewModel getHero() {
        return hero;
    }

    public void setHero(HeroViewModel hero) {
        this.hero = hero;
    }
}
