package com.tinqin.academy.db.service.impl.engineer;

import com.tinqin.academy.api.models.update.EngineerUpdateRequest;
import com.tinqin.academy.db.entities.Engineer;
import com.tinqin.academy.db.entities.Position;
import com.tinqin.academy.db.entities.Team;
import com.tinqin.academy.db.repositories.EngineerRepository;
import com.tinqin.academy.db.repositories.PositionRepository;
import com.tinqin.academy.db.repositories.TeamRepository;
import com.tinqin.academy.db.service.interfaces.UpdateService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateEngineerServiceImpl implements UpdateService<EngineerUpdateRequest> {
    private final EngineerRepository engineerRepository;
    private final PositionRepository positionRepository;
    private final TeamRepository teamRepository;

    public UpdateEngineerServiceImpl(EngineerRepository engineerRepository, PositionRepository positionRepository, TeamRepository teamRepository) {
        this.engineerRepository = engineerRepository;
        this.positionRepository = positionRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public void update(Long id, EngineerUpdateRequest editRequest) {
        Engineer engineer=engineerRepository.findById(id).orElseThrow();
        Optional<Team> team=Optional.ofNullable(teamRepository.getTeamByTeamName(editRequest.getTeamName()));
        Optional<Position> position=Optional.ofNullable(positionRepository.getPositionByPositionName(editRequest.getPositionName()));

        team.ifPresent(engineer::setTeam);
        position.ifPresent(engineer::setPosition);

    }
}
