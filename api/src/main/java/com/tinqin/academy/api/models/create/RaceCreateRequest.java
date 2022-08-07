package com.tinqin.academy.api.models.create;

import com.tinqin.academy.api.base.OperationInput;
import com.tinqin.academy.api.models.get.requests.SeasonGetRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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
    private List<String> seasons;
}
