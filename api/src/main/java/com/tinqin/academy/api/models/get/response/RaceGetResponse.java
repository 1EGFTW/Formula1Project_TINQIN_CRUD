package com.tinqin.academy.api.models.get.response;

import com.tinqin.academy.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class RaceGetResponse implements OperationResult {
    private String circuitName;

    private Boolean isCompleted;

    private String winnerName;

    private Date date;

    private List<Integer> seasons;

}
