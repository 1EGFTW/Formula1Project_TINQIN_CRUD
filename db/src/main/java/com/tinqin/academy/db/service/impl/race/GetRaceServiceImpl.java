package com.tinqin.academy.db.service.impl.race;

import com.tinqin.academy.api.models.get.response.SeasonGetResponse;
import com.tinqin.academy.db.entities.Race;
import com.tinqin.academy.db.repositories.RaceRepository;
import com.tinqin.academy.db.service.interfaces.GetService;
import com.tinqin.academy.db.service.mapper.RaceResponseMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetRaceServiceImpl implements GetService<RaceResponseMapper> {
    private final RaceRepository raceRepository;

    public GetRaceServiceImpl(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    public RaceResponseMapper getById(Long id) {
        Race race=raceRepository.findById(id).orElseThrow();
        List<Integer> seasons=new ArrayList<>();
        if(race.getSeasons()!=null){
            seasons=race.getSeasons().stream()
                    .map(season -> season.getYear())
                    .collect(Collectors.toList());
        }
        if(seasons!=null){
            return RaceResponseMapper.builder()
                    .circuitName(race.getCircuitName())
                    .date(race.getDate())
                    .isCompleted(race.getCompleted())
                    .winnerLastName(null)
                    .winnerFirstName(null)
                    .seasons(seasons)
                    .build();
        }
        if(race.getWinner()!=null){
            return RaceResponseMapper.builder()
                    .circuitName(race.getCircuitName())
                    .date(race.getDate())
                    .isCompleted(race.getCompleted())
                    .winnerLastName(race.getWinner().getLastName())
                    .winnerFirstName(race.getWinner().getFirstName())
                    .seasons(seasons)
                    .build();
        }
        return RaceResponseMapper.builder()
                .circuitName(race.getCircuitName())
                .winnerFirstName(null)
                .winnerLastName(null)
                .date(race.getDate())
                .isCompleted(false)
                .seasons(null)
                .build();
    }
}
