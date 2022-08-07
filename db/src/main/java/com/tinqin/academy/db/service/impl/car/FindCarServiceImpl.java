package com.tinqin.academy.db.service.impl.car;

import com.tinqin.academy.api.models.get.requests.CarGetRequest;
import com.tinqin.academy.api.models.get.response.CarGetResponse;
import com.tinqin.academy.db.repositories.CarRepository;
import com.tinqin.academy.db.service.interfaces.FindService;
import com.tinqin.academy.db.service.mapper.CarResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FindCarServiceImpl implements FindService<CarGetRequest, CarResponseMapper> {
    private final CarRepository carRepository;

    public FindCarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarResponseMapper> find(CarGetRequest input) {
        return Stream.of(carRepository.getCarByModelName(input.getModelName()))
                .filter(Objects::nonNull)
                .map(car -> CarResponseMapper.builder()
                        .modelName(car.getModelName())
                        .horsepower(car.getHorsepower())
                        .torque(car.getTorque())
                        .teamName(car.getTeam().getTeamName())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
