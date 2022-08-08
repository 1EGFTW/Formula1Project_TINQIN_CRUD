package com.tinqin.academy.db.service.mapper;

import com.tinqin.academy.api.base.OperationResult;
import com.tinqin.academy.api.models.get.response.SeasonGetResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class RaceResponseMapper implements OperationResult {
    private String circuitName;

    private Boolean isCompleted;

    private String winnerFirstName;
    private String winnerLastName;

    private Date date;

    private List<Integer> seasons;
}
