package bg.softuni.heroes.configuration;

import bg.softuni.heroes.data.models.Gender;
import bg.softuni.heroes.data.models.Hero;
import bg.softuni.heroes.data.models.Item;
import bg.softuni.heroes.data.models.Slot;
import bg.softuni.heroes.service.models.heroes.HeroCreateServiceModel;
import bg.softuni.heroes.service.models.heroes.HeroDetailsServiceModel;
import bg.softuni.heroes.service.models.heroes.HeroProfileDetailsServiceModel;
import bg.softuni.heroes.service.models.items.CreateItemServiceModel;
import bg.softuni.heroes.service.models.items.ItemServiceModel;
import bg.softuni.heroes.web.view.models.items.CreateItemModel;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<HeroCreateServiceModel, Hero>() {
            @Override
            protected void configure() {
                using(stringGenderConverter()).map(source.getGender()).setGender(null);
            }
        });

        modelMapper.addMappings(new PropertyMap<CreateItemModel, CreateItemServiceModel>() {
            @Override
            protected void configure() {
                using(stringSlotConverter()).map(source.getSlot()).setSlot(null);
            }
        });

        modelMapper.addMappings(new PropertyMap<Item, ItemServiceModel>() {
            @Override
            protected void configure() {
                using(slotStringConverter()).map(source.getSlot()).setSlot(null);
            }
        });

        modelMapper.addMappings(new PropertyMap<Hero, HeroDetailsServiceModel>() {
            @Override
            protected void configure() {
                using(genderStringConverter()).map(source.getGender()).setGender(null);
                using(heroHasGauntlets()).map(source).setHasGauntlets(null);
                using(heroHasHelmet()).map(source).setHasHelmet(null);
                using(heroHasPads()).map(source).setHasPads(null);
                using(heroHasWeapon()).map(source).setHasWeapon(null);
                using(heroHasPauldron()).map(source).setHasPauldron(null);
            }
        });

        modelMapper.addMappings(new PropertyMap<Hero, HeroProfileDetailsServiceModel>() {
            @Override
            protected void configure() {
                using(genderStringConverter()).map(source.getGender()).setGender(null);
            }
        });

        return modelMapper;
    }

    public Converter<Gender, String> genderStringConverter() {
        return new AbstractConverter<Gender, String>() {
            @Override
            protected String convert(Gender source) {
                return source.name().toLowerCase();
            }
        };
    }

    public Converter<String, Slot> stringSlotConverter() {
        return new AbstractConverter<String, Slot>() {
            @Override
            protected Slot convert(String source) {
                Slot slot;
                try {
                    slot = Slot.valueOf(source.toUpperCase());
                } catch (IllegalArgumentException | NullPointerException ex) {
                    slot = Slot.WEAPON;
                }
                return slot;
            }
        };
    }

    public Converter<String, Gender> stringGenderConverter() {
        return new AbstractConverter<String, Gender>() {
            @Override
            protected Gender convert(String source) {
                Gender gender;
                try {
                    gender = Gender.valueOf(source.toUpperCase());
                } catch (IllegalArgumentException | NullPointerException ex) {
                    gender = Gender.MALE;
                }
                return gender;
            }
        };
    }

    public Converter<Slot, String> slotStringConverter() {
        return new AbstractConverter<Slot, String>() {
            @Override
            protected String convert(Slot source) {
                return source.name();
            }
        };
    }

    public Converter<Hero, Boolean> heroHasWeapon() {
        return new AbstractConverter<Hero, Boolean>() {
            @Override
            protected Boolean convert(Hero source) {
                return source.getItems().stream().anyMatch(i -> i.getSlot() == Slot.WEAPON);
            }
        };
    }

    public Converter<Hero, Boolean> heroHasGauntlets() {
        return new AbstractConverter<Hero, Boolean>() {
            @Override
            protected Boolean convert(Hero source) {
                return source.getItems().stream().anyMatch(i -> i.getSlot() == Slot.GAUNTLETS);
            }
        };
    }

    public Converter<Hero, Boolean> heroHasHelmet() {
        return new AbstractConverter<Hero, Boolean>() {
            @Override
            protected Boolean convert(Hero source) {
                return source.getItems().stream().anyMatch(i -> i.getSlot() == Slot.HELMET);
            }
        };
    }

    public Converter<Hero, Boolean> heroHasPads() {
        return new AbstractConverter<Hero, Boolean>() {
            @Override
            protected Boolean convert(Hero source) {
                return source.getItems().stream().anyMatch(i -> i.getSlot() == Slot.PADS);
            }
        };
    }

    public Converter<Hero, Boolean> heroHasPauldron() {
        return new AbstractConverter<Hero, Boolean>() {
            @Override
            protected Boolean convert(Hero source) {
                return source.getItems().stream().anyMatch(i -> i.getSlot() == Slot.PAULDRON);
            }
        };
    }
}
