package com.tinqin.academy.db.service.mapper;

import com.tinqin.academy.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter(AccessLevel.PRIVATE)
@Getter
public class DriverResponseMapper implements OperationResult {
    private String firstName;
    private String lastName;
    private String teamName;
    private String numberOfChampionShips;
    private String driverPoints;
}
