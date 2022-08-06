package com.tinqin.academy.api.models.create;

import com.tinqin.academy.api.base.OperationInput;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class SeasonCreateRequest  implements OperationInput {
    private Integer year;

    private String championName;

}
