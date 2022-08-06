package com.tinqin.academy.db.repositories;

import com.tinqin.academy.db.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    Car getCarByModelName(String modelName);
    List<Car> getCarsByTeamTeamName(String teamName);
}
