package com.tinqin.academy.db.service.impl.season;

import com.tinqin.academy.api.models.get.requests.SeasonGetRequest;
import com.tinqin.academy.api.models.get.response.RaceGetResponse;
import com.tinqin.academy.db.repositories.SeasonRepository;
import com.tinqin.academy.db.service.interfaces.FindService;
import com.tinqin.academy.db.service.mapper.SeasonResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FindSeasonServiceImpl implements FindService<SeasonGetRequest, SeasonResponseMapper> {
    private final SeasonRepository seasonRepository;

    public FindSeasonServiceImpl(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    @Override
    public List<SeasonResponseMapper> find(SeasonGetRequest input) {
        return Stream.of(seasonRepository.getSeasonByYear(input.getSeasonYear()))
                .filter(Objects::nonNull)
                .map(season -> SeasonResponseMapper.builder()
                        .year(String.valueOf(season.getYear()))
                        .races(season.getRaces()
                                .stream()
                                .map(race -> RaceGetResponse.builder()
                                        .circuitName(race.getCircuitName())
                                        .date(race.getDate())
                                        .year(race.getYear())
                                        .isCompleted(race.getCompleted())
                                        .winnerName(race.getWinner().getFirstName()+" "+race.getWinner().getLastName())
                                        .build())
                                .toList())
                        .winner(season.getChampion().getFirstName()+" "+season.getChampion().getLastName())
                        .build())
                .collect(Collectors.toList());
    }
}
