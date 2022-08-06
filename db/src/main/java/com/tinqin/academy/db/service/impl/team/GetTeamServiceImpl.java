package com.tinqin.academy.db.service.impl.team;

import com.tinqin.academy.db.entities.Team;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.tinqin.academy.db.repositories.TeamRepository;
import com.tinqin.academy.db.service.interfaces.GetService;
import com.tinqin.academy.db.service.mapper.TeamResponseMapper;

@Service
@Primary
public class GetTeamServiceImpl implements GetService<TeamResponseMapper> {
    private final TeamRepository teamRepository;

    public GetTeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public TeamResponseMapper getById(Long id) {
        Team team= teamRepository.findById(id).orElseThrow();
        return TeamResponseMapper.builder()
                .teamName(team.getTeamName())
                .championships(team.getNumberOfChampionships())
                .constructorPoints(team.getConstructorPoints())
                .build();
    }
}
