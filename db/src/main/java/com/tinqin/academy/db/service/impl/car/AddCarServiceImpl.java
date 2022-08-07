package com.tinqin.academy.db.service.impl.car;

import com.tinqin.academy.api.models.create.CarCreateRequest;
import com.tinqin.academy.db.entities.Car;
import com.tinqin.academy.db.entities.Engineer;
import com.tinqin.academy.db.entities.Position;
import com.tinqin.academy.db.entities.Team;
import com.tinqin.academy.db.repositories.CarRepository;
import com.tinqin.academy.db.repositories.TeamRepository;
import com.tinqin.academy.db.service.interfaces.AddService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AddCarServiceImpl implements AddService<CarCreateRequest> {
    private final CarRepository carRepository;
    private final TeamRepository teamRepository;

    public AddCarServiceImpl(CarRepository carRepository, TeamRepository teamRepository) {
        this.carRepository = carRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public Long add(CarCreateRequest request) {
        return Stream.of(carRepository.getCarByModelName(request.getModelName()))
                .peek(car -> {
                    if(car==null){
                        Team team=Optional.ofNullable(teamRepository.getTeamByTeamName(request.getTeamName()))
                                .orElseThrow();
                        carRepository.save(new Car(request.getModelName(),
                                request.getHorsepower(),
                                request.getTorque(),
                                team));
                    }
                })
                .map(car -> carRepository.getCarByModelName(request.getModelName()))
                .findFirst()
                .orElseThrow()
                .getId_car();
    }
}
