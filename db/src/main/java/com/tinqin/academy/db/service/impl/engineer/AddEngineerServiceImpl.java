package com.tinqin.academy.db.service.impl.engineer;

import com.tinqin.academy.api.models.create.EngineerCreateRequest;
import com.tinqin.academy.db.entities.Engineer;
import com.tinqin.academy.db.entities.Position;
import com.tinqin.academy.db.entities.Team;
import com.tinqin.academy.db.repositories.EngineerRepository;
import com.tinqin.academy.db.repositories.PositionRepository;
import com.tinqin.academy.db.repositories.TeamRepository;
import com.tinqin.academy.db.service.interfaces.AddService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AddEngineerServiceImpl implements AddService<EngineerCreateRequest> {
    private final EngineerRepository engineerRepository;
    private final TeamRepository teamRepository;
    private final PositionRepository positionRepository;

    public AddEngineerServiceImpl(EngineerRepository engineerRepository, TeamRepository teamRepository, PositionRepository positionRepository) {
        this.engineerRepository = engineerRepository;
        this.teamRepository = teamRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public Long add(EngineerCreateRequest request) {
        return Stream.of(engineerRepository.getEngineerByFirstName(request.getFirstName()))
                .peek(engineer -> {
                        if(engineer==null){
                            Team team= Optional.ofNullable(teamRepository
                                            .getTeamByTeamName(request.getTeamName()))
                                    .orElseThrow();
                            Position position=Optional.ofNullable(positionRepository
                                            .getPositionByPositionName(request.getPositionName()))
                                    .orElseThrow();
                            engineerRepository.save(Engineer.builder()
                                            .firstName(request.getFirstName())
                                            .lastName(request.getLastName())
                                            .team(team)
                                            .position(position)
                                    .build());
                        }
                })
                .map(engineer -> engineerRepository.getEngineerByFirstName(request.getFirstName()))
                .findFirst()
                .orElseThrow()
                .getId_engineer();
    }
}
