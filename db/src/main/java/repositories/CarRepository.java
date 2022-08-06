package repositories;

import entities.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car,Long> {
    Car getCarByModelName(String modelName);
    List<Car> getCarsByTeamTeamName(String teamName);
}