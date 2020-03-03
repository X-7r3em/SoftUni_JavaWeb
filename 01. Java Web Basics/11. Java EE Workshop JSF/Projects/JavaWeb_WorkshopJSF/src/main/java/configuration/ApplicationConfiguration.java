package configuration;

import domain.entity.JobApplication;
import domain.entity.Sector;
import domain.service.JobApplicationServiceModel;
import domain.view.JobApplicationViewModel;
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
        return Persistence.createEntityManagerFactory("sboj_db")
                .createEntityManager();
    }

    @Produces
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<Sector, String> sectorStringConverter = new AbstractConverter<Sector, String>() {
            @Override
            protected String convert(Sector source) {
                return source.name().toLowerCase();
            }
        };

        modelMapper.addMappings(new PropertyMap<JobApplicationServiceModel, JobApplicationViewModel>() {
            @Override
            protected void configure() {
                using(sectorStringConverter).map(source.getSector()).setSector(null);
            }
        });

        return modelMapper;
    }
}
