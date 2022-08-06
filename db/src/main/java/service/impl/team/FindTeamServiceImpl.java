package service.impl.team;

import com.tinqin.academy.api.models.get.requests.TeamGetRequest;
import org.springframework.stereotype.Service;
import repositories.TeamRepository;
import service.interfaces.FindService;
import service.mapper.TeamResponseMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FindTeamServiceImpl implements FindService<TeamGetRequest, TeamResponseMapper> {
    private final TeamRepository teamRepository;

    public FindTeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    @Override
    public List<TeamResponseMapper> find(TeamGetRequest input) {
        return teamRepository.getTeamByTeamName(input.getTeamName())
                .stream()
                .filter(Objects::nonNull)
                .map(team -> TeamResponseMapper.builder()
                        .teamName(team.getTeamName())
                        .championships(team.getNumberOfChampionships())
                        .constructorPoints(team.getConstructorPoints())
                        .build())
                .collect(Collectors.toList());
    }
}
