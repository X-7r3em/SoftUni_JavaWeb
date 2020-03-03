package bg.softuni.filtersandauthentication.service.services;

import bg.softuni.filtersandauthentication.data.models.Role;
import bg.softuni.filtersandauthentication.data.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void seedRolesInDb() {
        roleRepository.saveAndFlush(new Role("ADMIN"));
        roleRepository.saveAndFlush(new Role("USER"));
    }
}
