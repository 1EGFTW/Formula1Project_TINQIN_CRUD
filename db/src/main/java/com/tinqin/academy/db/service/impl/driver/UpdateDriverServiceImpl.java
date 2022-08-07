package com.tinqin.academy.db.service.impl.driver;

import com.tinqin.academy.api.models.update.DriverUpdateRequest;
import com.tinqin.academy.db.entities.Car;
import com.tinqin.academy.db.entities.Driver;
import com.tinqin.academy.db.entities.DriverType;
import com.tinqin.academy.db.entities.Team;
import com.tinqin.academy.db.repositories.CarRepository;
import com.tinqin.academy.db.repositories.DriverRepository;
import com.tinqin.academy.db.repositories.TeamRepository;
import com.tinqin.academy.db.service.interfaces.UpdateService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateDriverServiceImpl implements UpdateService<DriverUpdateRequest> {
    private final DriverRepository driverRepository;
    private final CarRepository carRepository;
    private final TeamRepository teamRepository;

    public UpdateDriverServiceImpl(DriverRepository driverRepository, CarRepository carRepository, TeamRepository teamRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public void update(Long id, DriverUpdateRequest editRequest) {
        Driver driver=driverRepository.findById(id).orElseThrow();
        Optional<Car> car=Optional.ofNullable(carRepository.getCarByModelName(editRequest.getCarModel()));
        Optional<Team> team=Optional.ofNullable(teamRepository.getTeamByTeamName(editRequest.getTeamName()));
        if(car.isPresent())
            driver.setCar(car.get());
        if(team.isPresent())
            driver.setTeam(team.get());

        driver.setDriverPoints(editRequest.getPoints());
        driver.setAge(editRequest.getAge());
        driver.setDriverType(Enum.valueOf(DriverType.class,editRequest.getDriverType()));

        driverRepository.save(driver);
    }
}
