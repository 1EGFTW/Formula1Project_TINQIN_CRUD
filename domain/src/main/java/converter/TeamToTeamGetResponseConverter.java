package converter;

import com.tinqin.academy.api.models.create.TeamCreateRequest;
import com.tinqin.academy.api.models.get.response.TeamGetResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import service.mapper.TeamResponseMapper;
@Component
public class TeamToTeamGetResponseConverter implements Converter<TeamResponseMapper, TeamGetResponse> {
    @Override
    public TeamGetResponse convert(TeamResponseMapper source) {
        return TeamGetResponse.builder()
                .teamName(source.getTeamName())
                .championships(String.valueOf(source.getChampionships()))
                .points(String.valueOf(source.getConstructorPoints()))
                .build();
    }
}
