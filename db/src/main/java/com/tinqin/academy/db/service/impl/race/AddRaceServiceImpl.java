package com.tinqin.academy.db.service.impl.race;

import com.tinqin.academy.api.models.create.RaceCreateRequest;
import com.tinqin.academy.db.entities.Driver;
import com.tinqin.academy.db.entities.Race;
import com.tinqin.academy.db.entities.Season;
import com.tinqin.academy.db.repositories.DriverRepository;
import com.tinqin.academy.db.repositories.RaceRepository;
import com.tinqin.academy.db.repositories.SeasonRepository;
import com.tinqin.academy.db.service.interfaces.AddService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AddRaceServiceImpl implements AddService<RaceCreateRequest> {
    private final RaceRepository raceRepository;
    private final DriverRepository driverRepository;
    private final SeasonRepository seasonRepository;

    public AddRaceServiceImpl(RaceRepository raceRepository, DriverRepository driverRepository, SeasonRepository seasonRepository) {
        this.raceRepository = raceRepository;
        this.driverRepository = driverRepository;
        this.seasonRepository = seasonRepository;
    }

    @Override
    public Long add(RaceCreateRequest request) {//needs to be cleaned up
        Date date=request.getDate();
        Integer year= date.getYear()+1900;
        return Stream.of(raceRepository.getRaceByCircuitNameAndDate(request.getCircuitName(),date))
                .peek(race -> {
                    if(race==null){
                        List<Season> seasons= request.getSeasons().stream()
                                .map(season -> seasonRepository.getSeasonByYear(season))
                                .toList();
                        Driver driver=driverRepository.getDriverByFirstNameAndLastName(request.getWinnerFirstName(),
                                request.getWinnerLastName());
                        Race raceToAdd=new Race(request.getCircuitName(),
                                request.getIsCompleted(),
                                driver,
                                request.getDate(),
                                request.getNumberOfLaps(),
                                request.getDistancePerLap(),
                                request.getLatitude(),
                                request.getLongitude(),
                                seasons);

                        raceRepository.save(raceToAdd);
                        Race savedRace=raceRepository.getRaceByCircuitNameAndDate(request.getCircuitName(), date);
                        seasons.stream()
                                        .forEach(season -> {
                                            season.addRace(savedRace);
                                            seasonRepository.save(season);
                                        });
                    }
                })
                .map(race ->raceRepository.getRaceByCircuitNameAndDate(request.getCircuitName(), date))
                .findFirst()
                .orElseThrow()
                .getId_race();
    }
}
