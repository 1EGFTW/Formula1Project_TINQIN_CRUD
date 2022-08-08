package com.tinqin.academy.db.service.impl.race;

import com.tinqin.academy.api.models.get.requests.RaceGetRequest;
import com.tinqin.academy.api.models.get.response.SeasonGetResponse;
import com.tinqin.academy.db.entities.Season;
import com.tinqin.academy.db.repositories.RaceRepository;
import com.tinqin.academy.db.repositories.SeasonRepository;
import com.tinqin.academy.db.service.interfaces.FindService;
import com.tinqin.academy.db.service.mapper.RaceResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FindRaceServiceImpl implements FindService<RaceGetRequest, RaceResponseMapper> {
    private final RaceRepository raceRepository;
    private final SeasonRepository seasonRepository;

    public FindRaceServiceImpl(RaceRepository raceRepository, SeasonRepository seasonRepository) {
        this.raceRepository = raceRepository;
        this.seasonRepository = seasonRepository;
    }

    @Override
    public List<RaceResponseMapper> find(RaceGetRequest input) {
        Season season=seasonRepository.getSeasonByYear(input.getSeasons().getSeasonYear());
        return raceRepository.findAll().stream()
                .filter(race -> race.getSeasons().contains(season))
                .map(race -> RaceResponseMapper.builder()
                        .date(race.getDate())
                        .isCompleted(race.getCompleted())
                        .winnerFirstName(race.getWinner().getFirstName())
                        .winnerLastName(race.getWinner().getLastName())
                        .circuitName(race.getCircuitName())
                        .seasons(race.getSeasons().stream()
                                .map(season1 -> season1.getYear())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }
}
