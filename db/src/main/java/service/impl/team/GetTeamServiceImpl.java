package service.impl.team;

import entities.Team;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import repositories.TeamRepository;
import service.interfaces.GetService;
import service.mapper.TeamResponseMapper;

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
