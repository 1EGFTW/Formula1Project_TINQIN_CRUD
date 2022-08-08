package com.tinqin.academy.db.service.impl.race;

import com.tinqin.academy.db.entities.Race;
import com.tinqin.academy.db.entities.Season;
import com.tinqin.academy.db.repositories.RaceRepository;
import com.tinqin.academy.db.repositories.SeasonRepository;
import com.tinqin.academy.db.service.interfaces.DeleteService;
import org.springframework.stereotype.Service;

@Service("race")
public class DeleteRaceServiceImpl implements DeleteService {
    private final RaceRepository raceRepository;

    public DeleteRaceServiceImpl(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    public void delete(Long id) {
        Race race=raceRepository.findById(id).orElseThrow();
        raceRepository.delete(race);

    }
}
