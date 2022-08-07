package com.tinqin.academy.db.service.mapper;

import com.tinqin.academy.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class PositionResponseMapper implements OperationResult {
    private String positionName;
}
