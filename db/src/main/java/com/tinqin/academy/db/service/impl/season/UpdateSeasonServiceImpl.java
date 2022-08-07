package com.tinqin.academy.db.service.impl.season;

import com.tinqin.academy.api.models.update.SeasonUpdateRequest;
import com.tinqin.academy.db.entities.Driver;
import com.tinqin.academy.db.entities.Race;
import com.tinqin.academy.db.entities.Season;
import com.tinqin.academy.db.repositories.DriverRepository;
import com.tinqin.academy.db.repositories.RaceRepository;
import com.tinqin.academy.db.repositories.SeasonRepository;
import com.tinqin.academy.db.service.interfaces.UpdateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UpdateSeasonServiceImpl implements UpdateService<SeasonUpdateRequest> {
    private final SeasonRepository seasonRepository;
    private final RaceRepository raceRepository;
    private final DriverRepository driverRepository;

    public UpdateSeasonServiceImpl(SeasonRepository seasonRepository, RaceRepository raceRepository, DriverRepository driverRepository) {
        this.seasonRepository = seasonRepository;
        this.raceRepository = raceRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public void update(Long id, SeasonUpdateRequest editRequest) {
        Season season=seasonRepository.findById(id).orElseThrow();
        List<Race> races= editRequest.getNewRaces().stream()
                .map(race -> {
                    return Optional.ofNullable(raceRepository.getRaceByCircuitName(race))
                            .orElseThrow();
                }).toList();
        season.setRaces(races);
        Driver driver= Optional.ofNullable(driverRepository
                        .getDriverByFirstNameAndLastName(editRequest.getChampionFirstName(),
                                editRequest.getChampionLastName()))
                .orElseThrow();
        season.setChampion(driver);
        seasonRepository.save(season);

    }
}
