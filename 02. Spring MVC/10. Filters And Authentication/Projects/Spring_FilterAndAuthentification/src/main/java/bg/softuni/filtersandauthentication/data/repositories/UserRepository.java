package bg.softuni.filtersandauthentication.data.repositories;

import bg.softuni.filtersandauthentication.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
