package com.tinqin.academy.api.models.create;

import com.tinqin.academy.api.operations.OperationInput;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class EngineerCreateRequest  implements OperationInput {
    private String firstName;

    private String lastName;

    private String teamName;

    private String positionName;
}
