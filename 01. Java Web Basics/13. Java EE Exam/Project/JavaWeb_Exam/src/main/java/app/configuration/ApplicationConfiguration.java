package app.configuration;

import app.domain.entity.ClassType;
import app.domain.entity.Hero;
import app.domain.model.service.HeroServiceModel;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ApplicationConfiguration {
    @Produces
    public EntityManager entityManager() {
        return Persistence.createEntityManagerFactory("heroes_db")
                .createEntityManager();
    }

    @Produces
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, ClassType> stringClassTypeConverter = new AbstractConverter<String, ClassType>() {
            @Override
            protected ClassType convert(String source) {
                return ClassType.valueOf(source);
            }
        };

        modelMapper.addMappings(new PropertyMap<HeroServiceModel, Hero>() {
            @Override
            protected void configure() {
                using(stringClassTypeConverter).map(source.getClassType()).setClassType(null);
            }
        });

        Converter<ClassType, String> classTypeStringConverter = new AbstractConverter<ClassType, String>() {
            @Override
            protected String convert(ClassType source) {
                return source.name();
            }
        };

        modelMapper.addMappings(new PropertyMap<Hero, HeroServiceModel>() {
            @Override
            protected void configure() {
                using(classTypeStringConverter).map(source.getClassType()).setClassType(null);
            }
        });

        return modelMapper;
    }
}

