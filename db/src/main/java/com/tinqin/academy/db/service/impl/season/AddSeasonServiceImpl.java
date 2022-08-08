package com.tinqin.academy.db.service.impl.season;

import com.tinqin.academy.api.models.create.SeasonCreateRequest;
import com.tinqin.academy.db.entities.Driver;
import com.tinqin.academy.db.entities.Race;
import com.tinqin.academy.db.entities.Season;
import com.tinqin.academy.db.repositories.DriverRepository;
import com.tinqin.academy.db.repositories.RaceRepository;
import com.tinqin.academy.db.repositories.SeasonRepository;
import com.tinqin.academy.db.service.interfaces.AddService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AddSeasonServiceImpl implements AddService<SeasonCreateRequest> {
    private final SeasonRepository seasonRepository;
    private final DriverRepository driverRepository;
    private final RaceRepository raceRepository;

    public AddSeasonServiceImpl(SeasonRepository seasonRepository, DriverRepository driverRepository, RaceRepository raceRepository) {
        this.seasonRepository = seasonRepository;
        this.driverRepository = driverRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public Long add(SeasonCreateRequest request) {
        return Stream.of(seasonRepository.getSeasonByYear(request.getYear()))
                .peek(season -> {
                    if(season==null){
                        Driver driver= driverRepository
                                .getDriverByFirstNameAndLastName(request.getChampionFirstName(),
                                        request.getChampionLastName());
                        List<Race> races= request.getRaces().stream()
                                .map(raceRepository::getRaceByCircuitName)
                                .toList();
                       seasonRepository.save(new Season(request.getYear(),driver,races));
                    }
                })
                .map(season -> seasonRepository.getSeasonByYear(request.getYear()))
                .findFirst()
                .orElseThrow()
                .getId_season();
    }
}
