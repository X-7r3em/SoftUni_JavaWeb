package configuration;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Configuration {
    @Produces
    public EntityManager entityManager() {
        return Persistence
                .createEntityManagerFactory("courses_db")
                .createEntityManager();
    }

    @Produces
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
