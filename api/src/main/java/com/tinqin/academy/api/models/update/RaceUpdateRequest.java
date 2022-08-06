package com.tinqin.academy.api.models.update;

import com.tinqin.academy.api.base.OperationInput;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class RaceUpdateRequest implements OperationInput {
    private Boolean isCompleted;
    private String winnerName;
    private Date date;
}
