package configuration;

import domain.entity.Gender;
import domain.model.binding.UserRegisterBindingModel;
import domain.model.service.UserServiceModel;
import domain.model.view.UserViewModel;
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
        return Persistence.createEntityManagerFactory("casebook_db")
                .createEntityManager();
    }

    @Produces
    public ModelMapper modelMapper() {
        ModelMapper modelMapper =  new ModelMapper();

        Converter<String, Gender> stringGenderConverter = new AbstractConverter<String, Gender>() {
            @Override
            protected Gender convert(String source) {
                try {
                    return Gender.valueOf(source.toUpperCase());
                } catch (Exception e) {
                    return null;
                }
            }
        };

        modelMapper.addMappings(new PropertyMap<UserRegisterBindingModel, UserServiceModel>() {
            @Override
            protected void configure() {
                using(stringGenderConverter).map(source.getGender()).setGender(null);
            }
        });

        Converter<Gender, String> genderStringConverter = new AbstractConverter<Gender, String>() {
            @Override
            protected String convert(Gender source) {
                return source.name().toLowerCase();
            }
        };

        modelMapper.addMappings(new PropertyMap<UserServiceModel, UserViewModel>() {
            @Override
            protected void configure() {
                using(genderStringConverter).map(source.getGender()).setGender(null);
            }
        });
        return modelMapper;
    }
}
