package com.tinqin.academy.db.service.mapper;

import com.tinqin.academy.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter(AccessLevel.PRIVATE)
@Getter
public class CarResponseMapper implements OperationResult {
    private String modelName;

    private Integer horsepower;

    private Integer torque;

    private String teamName;
}
