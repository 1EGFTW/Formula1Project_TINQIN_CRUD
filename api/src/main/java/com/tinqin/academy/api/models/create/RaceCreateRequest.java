package com.tinqin.academy.api.models.create;

import com.tinqin.academy.api.operations.OperationInput;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class RaceCreateRequest  implements OperationInput {
    private String circuitName;

    private Integer year;

    private Boolean isCompleted;

    private String winnerName;

    private Date date;

    private Integer numberOfLaps;

    private Double distancePerLap;

    private Double latitude;

    private Double longitude;
}
