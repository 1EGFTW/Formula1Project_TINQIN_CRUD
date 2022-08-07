package com.tinqin.academy.api.models.get.requests;

import com.tinqin.academy.api.base.OperationInput;
import com.tinqin.academy.api.models.get.response.SeasonGetResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class RaceGetRequest  implements OperationInput {
    private String circuitName;
    private SeasonGetRequest seasons;
}
