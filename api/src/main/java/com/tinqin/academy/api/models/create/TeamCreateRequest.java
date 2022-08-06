package com.tinqin.academy.api.models.create;

import com.tinqin.academy.api.base.OperationInput;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class TeamCreateRequest  implements OperationInput {
    private String teamName;

    private Integer numberOfChampionships;

    private Integer constructorPoints;
}
