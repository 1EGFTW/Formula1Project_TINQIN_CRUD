package com.tinqin.academy.db.service.impl.team;

import com.tinqin.academy.api.models.get.requests.TeamGetRequest;
import org.springframework.stereotype.Service;
import com.tinqin.academy.db.repositories.TeamRepository;
import com.tinqin.academy.db.service.interfaces.FindService;
import com.tinqin.academy.db.service.mapper.TeamResponseMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FindTeamServiceImpl implements FindService<TeamGetRequest,TeamResponseMapper> {
    private final TeamRepository teamRepository;

    public FindTeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    @Override
    public List<TeamResponseMapper> find(TeamGetRequest input) {
        return Stream.of(teamRepository.getTeamByTeamName(input.getTeamName()))
                .filter(Objects::nonNull)
                .map(team -> TeamResponseMapper.builder()
                        .teamName(team.getTeamName())
                        .championships(team.getNumberOfChampionships())
                        .constructorPoints(team.getConstructorPoints())
                        .build())
                .collect(Collectors.toList());
    }
}
