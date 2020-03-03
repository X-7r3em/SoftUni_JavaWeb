package app.web;

import app.domain.model.binding.HeroBindingModel;
import app.domain.model.service.HeroServiceModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class HeroCreateBean extends BaseBean {
    private HeroBindingModel hero;

    private HeroService heroService;
    private ModelMapper modelMapper;

    public HeroCreateBean() {
    }

    @Inject
    public HeroCreateBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.hero = new HeroBindingModel();
    }

    public void create() {
        if (this.heroService.getByName(this.hero.getName()) != null
                || this.hero.getLevel() < 0) {
            this.redirect("/hero-create");
            return;
        }

        this.heroService.save(this.modelMapper.map(this.hero, HeroServiceModel.class));
        this.redirect("/home");
    }

    public HeroBindingModel getHero() {
        return hero;
    }

    public void setHero(HeroBindingModel hero) {
        this.hero = hero;
    }
}
