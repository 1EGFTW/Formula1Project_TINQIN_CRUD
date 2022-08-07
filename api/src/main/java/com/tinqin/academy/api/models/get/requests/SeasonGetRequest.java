package com.tinqin.academy.api.models.get.requests;

import com.tinqin.academy.api.base.OperationInput;
import com.tinqin.academy.api.models.get.response.RaceGetResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class SeasonGetRequest  implements OperationInput {
    private Integer seasonYear;
}
