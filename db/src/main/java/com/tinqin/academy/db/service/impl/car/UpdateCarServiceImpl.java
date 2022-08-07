package com.tinqin.academy.db.service.impl.car;

import com.tinqin.academy.api.models.update.CarUpdateRequest;
import com.tinqin.academy.db.entities.Car;
import com.tinqin.academy.db.repositories.CarRepository;
import com.tinqin.academy.db.service.interfaces.UpdateService;
import org.springframework.stereotype.Service;

@Service
public class UpdateCarServiceImpl implements UpdateService<CarUpdateRequest> {
    private final CarRepository carRepository;

    public UpdateCarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void update(Long id, CarUpdateRequest editRequest) {
        Car car=carRepository.findById(id).orElseThrow();
        car.setModelName(editRequest.getModelName());
        car.setHorsepower(editRequest.getHorsepower());
        car.setTorque(editRequest.getTorque());
        carRepository.save(car);
    }
}
