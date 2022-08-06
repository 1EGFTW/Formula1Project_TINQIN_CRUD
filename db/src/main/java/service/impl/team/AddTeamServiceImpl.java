package service.impl.team;

import com.tinqin.academy.api.models.create.TeamCreateRequest;
import entities.Team;
import org.springframework.stereotype.Service;
import repositories.TeamRepository;
import service.interfaces.AddService;

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
        return teamRepository.getTeamByTeamName(request.getTeamName())
                .stream()
                .filter(Objects::isNull)
                .peek(team -> teamRepository.save(Team.builder()
                                .teamName(request.getTeamName())
                                .numberOfChampionships(request.getNumberOfChampionships())
                                .constructorPoints(request.getConstructorPoints())
                        .build()))
                .findFirst()
                .orElseThrow()
                .getId_team();
    }
}
