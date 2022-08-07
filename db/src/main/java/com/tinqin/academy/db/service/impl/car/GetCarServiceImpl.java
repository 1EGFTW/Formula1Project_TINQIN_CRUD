package com.tinqin.academy.db.service.impl.car;

import com.tinqin.academy.db.entities.Car;
import com.tinqin.academy.db.repositories.CarRepository;
import com.tinqin.academy.db.service.interfaces.GetService;
import com.tinqin.academy.db.service.mapper.CarResponseMapper;
import org.springframework.stereotype.Service;

@Service
public class GetCarServiceImpl implements GetService<CarResponseMapper> {
    private final CarRepository carRepository;

    public GetCarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarResponseMapper getById(Long id) {
        Car car=carRepository.findById(id).orElseThrow();
        return CarResponseMapper.builder()
                .modelName(car.getModelName())
                .horsepower(car.getHorsepower())
                .torque(car.getTorque())
                .teamName(car.getTeam().getTeamName())
                .build();
    }
}
