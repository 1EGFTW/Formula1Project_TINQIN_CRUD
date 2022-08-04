package com.tinqin.academy.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import repositories.*;

@RestController
public class HomeController {
   /* private final CarRepository carRepository;
    private final DriverRepository driverRepository;
    private final RaceRepository raceRepository;
    private final RaceSeasonRepository raceSeasonRepository;
    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;
    private final EngineerRepository engineerRepository;
    private final PositionRepository positionRepository;

    public HomeController(CarRepository carRepository, DriverRepository driverRepository, RaceRepository raceRepository, RaceSeasonRepository raceSeasonRepository, SeasonRepository seasonRepository, TeamRepository teamRepository, EngineerRepository engineerRepository, PositionRepository positionRepository) {
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
        this.raceRepository = raceRepository;
        this.raceSeasonRepository = raceSeasonRepository;
        this.seasonRepository = seasonRepository;
        this.teamRepository = teamRepository;
        this.engineerRepository = engineerRepository;
        this.positionRepository = positionRepository;
    }*/
    private final CarRepository carRepository;

    public HomeController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/")
    public String home(){
        return "Welcome";
    }
}
