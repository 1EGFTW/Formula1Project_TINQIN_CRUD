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
    public Long add(RaceCreateRequest request) { //needs to be cleaned up
        return Stream.of(raceRepository.getRaceByCircuitNameAndYear(request.getCircuitName(), request.getYear()))
                .peek(race -> {
                    if(race==null){
                        Season season=seasonRepository.getSeasonByYear(request.getYear());
                        Driver driver=driverRepository.getDriverByFirstNameAndLastName(request.getWinnerFirstName(),
                                request.getWinnerLastName());
                        Race raceToAdd=new Race(request.getCircuitName(),
                                request.getYear(),
                                request.getIsCompleted(),
                                driver,
                                request.getDate(),
                                request.getNumberOfLaps(),
                                request.getDistancePerLap(),
                                request.getLatitude(),
                                request.getLongitude());
                        raceToAdd.addSeason(season);
                        raceRepository.save(raceToAdd);
                        /*raceToAdd=raceRepository.getRaceByCircuitNameAndYear(request.getCircuitName(), request.getYear());
                        raceToAdd.addSeason(season);
                        raceRepository.save(raceToAdd);*/
                        season.addRace(raceToAdd);
                        seasonRepository.save(season);
                    }
                })
                .map(race ->raceRepository.getRaceByCircuitNameAndYear(request.getCircuitName(), request.getYear()))
                .findFirst()
                .orElseThrow()
                .getId_race();
    }
}
