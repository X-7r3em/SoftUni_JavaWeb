package bg.softuni.filtersandauthentication.service.services;

import bg.softuni.filtersandauthentication.data.models.User;
import bg.softuni.filtersandauthentication.data.repositories.RoleRepository;
import bg.softuni.filtersandauthentication.data.repositories.UserRepository;
import bg.softuni.filtersandauthentication.service.models.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails = this.userRepository.findByEmail(email);
        if (userDetails == null) {
            throw new UsernameNotFoundException("No such user!");
        }
        return userDetails;
    }

    @Override
    public User register(UserServiceModel model) {
        User user = modelMapper.map(model, User.class);
        if (userRepository.count() == 0) {
            roleService.seedRolesInDb();

            user.setAuthorities(new HashSet<>(roleRepository.findAll()));
        } else {
            user.setAuthorities(new HashSet<>(Set.of(roleRepository.findByAuthority("USER"))));
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }
}
