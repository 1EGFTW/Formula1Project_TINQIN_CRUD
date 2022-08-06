package com.tinqin.academy.api.models.get.response;

import com.tinqin.academy.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class RaceGetResponse implements OperationResult {
    private String circuitName;

    private Integer year;

    private Boolean isCompleted;

    private String winnerName;

    private Date date;

    private String temp;

    private String condition;
}
