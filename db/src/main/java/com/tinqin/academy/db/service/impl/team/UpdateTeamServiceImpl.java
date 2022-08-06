package com.tinqin.academy.db.service.impl.team;

import com.tinqin.academy.api.models.update.TeamUpdateRequest;
import com.tinqin.academy.db.entities.Team;
import org.springframework.stereotype.Service;
import com.tinqin.academy.db.repositories.TeamRepository;
import com.tinqin.academy.db.service.interfaces.UpdateService;
@Service
public class UpdateTeamServiceImpl implements UpdateService<TeamUpdateRequest> {
    private final TeamRepository teamRepository;

    public UpdateTeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void update(Long id, TeamUpdateRequest editRequest) {
        Team team=teamRepository.findById(id).orElseThrow();
        team.setConstructorPoints(editRequest.getConstructorPoints());
        team.setNumberOfChampionships(editRequest.getNumberOfChampionShips());
        teamRepository.save(team);
    }
}
