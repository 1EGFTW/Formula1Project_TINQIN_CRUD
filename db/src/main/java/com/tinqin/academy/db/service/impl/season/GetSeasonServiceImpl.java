package com.tinqin.academy.db.service.impl.season;

import com.tinqin.academy.api.models.get.response.RaceGetResponse;
import com.tinqin.academy.db.entities.Season;
import com.tinqin.academy.db.repositories.SeasonRepository;
import com.tinqin.academy.db.service.interfaces.GetService;
import com.tinqin.academy.db.service.mapper.SeasonResponseMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GetSeasonServiceImpl implements GetService<SeasonResponseMapper> {
    private final SeasonRepository seasonRepository;

    public GetSeasonServiceImpl(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    @Override
    public SeasonResponseMapper getById(Long id) { //da se popravi na po-kysen etap ako driver ili race e null
        Season season=seasonRepository.findById(id).orElseThrow();
        List<RaceGetResponse> races=new ArrayList<>();
        if(season.getRaces()!=null){
            races=season.getRaces()
                    .stream()
                    .map(race -> RaceGetResponse.builder()
                            .circuitName(race.getCircuitName())
                            .date(race.getDate())
                            .isCompleted(race.getCompleted())
                            .winnerName(race.getWinner().getFirstName()+" "+race.getWinner().getLastName())
                            .build())
                    .toList();
        }
        if(races!=null){
            if(season.getChampion()!=null){
                return SeasonResponseMapper.builder()
                        .year(String.valueOf(season.getYear()))
                        .races(races)
                        .winner(season.getChampion().getFirstName() + " "+ season.getChampion().getLastName())
                        .build();
            }
        }

        return SeasonResponseMapper.builder()
                .year(String.valueOf(season.getYear()))
                .races(null)
                .winner(null)
                .build();


    }
}
