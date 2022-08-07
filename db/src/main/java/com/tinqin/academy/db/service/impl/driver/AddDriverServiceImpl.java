package com.tinqin.academy.db.service.impl.driver;

import com.tinqin.academy.api.models.create.DriverCreateRequest;
import com.tinqin.academy.db.entities.Car;
import com.tinqin.academy.db.entities.Driver;
import com.tinqin.academy.db.entities.DriverType;
import com.tinqin.academy.db.entities.Team;
import com.tinqin.academy.db.repositories.CarRepository;
import com.tinqin.academy.db.repositories.DriverRepository;
import com.tinqin.academy.db.repositories.TeamRepository;
import com.tinqin.academy.db.service.interfaces.AddService;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class AddDriverServiceImpl implements AddService<DriverCreateRequest> {
    private final CarRepository carRepository;
    private final TeamRepository teamRepository;
    private final DriverRepository driverRepository;

    public AddDriverServiceImpl(CarRepository carRepository, TeamRepository teamRepository, DriverRepository driverRepository) {
        this.carRepository = carRepository;
        this.teamRepository = teamRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public Long add(DriverCreateRequest request) {
        return Stream.of(driverRepository.getDriverByFirstNameAndLastName(request.getFirstName(), request.getLastName()))
                .peek(driver -> {
                    if(driver==null){
                        Car driverCar=carRepository.findAll().stream()
                                .filter(car -> car.getModelName().equals(request.getCarModel()))
                                .findFirst()
                                .orElseThrow();
                        Team driverTeam=teamRepository.findAll().stream()
                                .filter(team -> team.getTeamName().equals(request.getTeamName()))
                                .findFirst()
                                .orElseThrow();
                        String driverType;
                        if(request.getDriverType().equals(String.valueOf(DriverType.SECONDARY_DRIVER))){
                            driverType= String.valueOf(DriverType.SECONDARY_DRIVER);
                        }
                        else{
                            driverType=String.valueOf(DriverType.PRIMARY_DRIVER);
                        }
                        driverRepository.save(new Driver(request.getFirstName(),
                                request.getLastName(),
                                request.getAge(),
                                request.getDriverPoints(),
                                request.getChampionships(),
                                Enum.valueOf(DriverType.class, request.getDriverType()),
                                driverTeam,
                                driverCar));
                    }
                })
                .map(driver -> driverRepository.getDriverByFirstNameAndLastName(request.getFirstName(), request.getLastName()))
                .findFirst()
                .orElseThrow()
                .getId_driver();
    }
}
