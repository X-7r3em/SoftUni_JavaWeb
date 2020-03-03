package bg.softuni.springessentials.data.repositories;

import bg.softuni.springessentials.data.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
}
