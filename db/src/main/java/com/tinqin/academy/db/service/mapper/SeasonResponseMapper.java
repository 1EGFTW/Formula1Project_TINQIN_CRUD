package com.tinqin.academy.db.service.mapper;

import com.tinqin.academy.api.base.OperationResult;
import com.tinqin.academy.api.models.get.response.RaceGetResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter(AccessLevel.PRIVATE)
@Getter
public class SeasonResponseMapper implements OperationResult {
    private String year;
    private String winner;
    private List<RaceGetResponse> races;
}
