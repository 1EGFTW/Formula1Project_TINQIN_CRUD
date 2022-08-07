package com.tinqin.academy.api.models.update;

import com.tinqin.academy.api.base.OperationInput;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class SeasonUpdateRequest implements OperationInput {
    private List<String> newRaces;
    private String championFirstName;
    private String championLastName;
}
