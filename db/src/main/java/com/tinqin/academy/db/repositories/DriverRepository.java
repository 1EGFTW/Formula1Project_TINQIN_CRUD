package com.tinqin.academy.db.repositories;

import com.tinqin.academy.db.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
    Driver getDriverByFirstNameAndLastName(String firstName,String lastName);
    Driver getDriverByDriverTypeContaining(String driverType);
    List<Driver> getDriversByTeamTeamName(String teamName);
}
