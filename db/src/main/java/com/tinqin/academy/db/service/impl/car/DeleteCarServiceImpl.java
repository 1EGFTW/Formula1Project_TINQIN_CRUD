package com.tinqin.academy.db.service.impl.car;

import com.tinqin.academy.db.repositories.CarRepository;
import com.tinqin.academy.db.service.interfaces.DeleteService;
import org.springframework.stereotype.Service;

@Service("car")
public class DeleteCarServiceImpl implements DeleteService {
    private final CarRepository carRepository;

    public DeleteCarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
