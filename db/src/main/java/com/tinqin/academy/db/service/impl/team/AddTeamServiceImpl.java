package com.tinqin.academy.db.service.impl.team;

import com.tinqin.academy.api.models.create.TeamCreateRequest;
import com.tinqin.academy.db.entities.Team;
import org.springframework.stereotype.Service;
import com.tinqin.academy.db.repositories.TeamRepository;
import com.tinqin.academy.db.service.interfaces.AddService;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AddTeamServiceImpl implements AddService<TeamCreateRequest> {
    private final TeamRepository teamRepository;

    public AddTeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Long add(TeamCreateRequest request) {
        return Stream.of(teamRepository.getTeamByTeamName(request.getTeamName()))
                .peek(team -> {
                    if(team==null){
                        teamRepository.save(Team.builder()
                                .teamName(request.getTeamName())
                                .numberOfChampionships(request.getNumberOfChampionships())
                                .constructorPoints(request.getConstructorPoints())
                                .build());
                    }
                })
                .map(team -> teamRepository.getTeamByTeamName(request.getTeamName()))
                .findFirst()
                .orElseThrow()
                .getId_team();
        /*Optional<Team> teamToAdd=Optional.ofNullable(teamRepository.getTeamByTeamName(request.getTeamName()));
        if(teamToAdd.isEmpty()){
            teamRepository.save(Team.builder()
                    .teamName(request.getTeamName())
                    .numberOfChampionships(request.getNumberOfChampionships())
                    .constructorPoints(request.getConstructorPoints())
                    .build());
        }
        return teamRepository.getTeamByTeamName(request.getTeamName()).getId_team();*/
    }
}
