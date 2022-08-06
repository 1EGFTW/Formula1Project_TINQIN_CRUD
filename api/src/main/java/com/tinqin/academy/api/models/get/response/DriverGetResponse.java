package com.tinqin.academy.api.models.get.response;

import com.tinqin.academy.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class DriverGetResponse implements OperationResult {
    private String firstName;
    private String lastName;
    private String teamName;
    private String numberOfChampionShips;
    private String constructorPoints;
}
