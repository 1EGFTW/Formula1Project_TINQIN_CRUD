package service.mapper;

import com.tinqin.academy.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter(AccessLevel.PRIVATE)
@Getter
public class TeamResponseMapper implements OperationResult {
    private String teamName;
    private Integer constructorPoints;
    private Integer championships;

}
