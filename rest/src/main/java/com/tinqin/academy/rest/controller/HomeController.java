package com.tinqin.academy.rest.controller;

import entities.Car;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import repositories.*;

@RestController
public class HomeController {
    private final CarRepository carRepository;

    public HomeController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @PostMapping("/test")
    public void home(){
        carRepository.save(new Car());
    }
}
