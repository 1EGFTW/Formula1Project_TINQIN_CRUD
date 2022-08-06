package com.tinqin.academy.db.service.impl.team;

import org.springframework.stereotype.Service;
import com.tinqin.academy.db.repositories.TeamRepository;
import com.tinqin.academy.db.service.interfaces.DeleteService;
@Service
public class DeleteTeamServiceImpl implements DeleteService {
    private final TeamRepository teamRepository;

    public DeleteTeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }
}
