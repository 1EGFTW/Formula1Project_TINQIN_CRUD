package com.tinqin.academy.api.models.create;

import com.tinqin.academy.api.base.OperationInput;
import com.tinqin.academy.api.models.get.requests.RaceGetRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class SeasonCreateRequest implements OperationInput {
    private Integer year;
    private String championFirstName;
    private String championLastName;
    private List<String> races;
}
