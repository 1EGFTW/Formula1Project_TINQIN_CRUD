package com.tinqin.academy.api.models.get.response;

import com.tinqin.academy.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class SeasonGetResponse implements OperationResult {
    private String year;
    private String winner;
    private List<RaceGetResponse> races;
}
