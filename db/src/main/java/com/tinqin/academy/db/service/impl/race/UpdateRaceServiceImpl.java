package com.tinqin.academy.db.service.impl.race;

import com.tinqin.academy.api.models.update.RaceUpdateRequest;
import com.tinqin.academy.db.entities.Driver;
import com.tinqin.academy.db.entities.Race;
import com.tinqin.academy.db.repositories.DriverRepository;
import com.tinqin.academy.db.repositories.RaceRepository;
import com.tinqin.academy.db.service.interfaces.UpdateService;
import org.springframework.stereotype.Service;

@Service
public class UpdateRaceServiceImpl implements UpdateService<RaceUpdateRequest> {
    private final RaceRepository raceRepository;
    private final DriverRepository driverRepository;

    public UpdateRaceServiceImpl(RaceRepository raceRepository, DriverRepository driverRepository) {
        this.raceRepository = raceRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public void update(Long id, RaceUpdateRequest editRequest) {
        Race race=raceRepository.findById(id).orElseThrow();
        Driver winner=driverRepository.getDriverByFirstNameAndLastName(editRequest.getWinnerFirstName(), editRequest.getWinnerLastName());
        race.setWinner(winner);
        race.setCompleted(editRequest.getIsCompleted());
        race.setDate(editRequest.getDate());
        raceRepository.save(race);
    }
}
