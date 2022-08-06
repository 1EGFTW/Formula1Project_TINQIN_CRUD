package com.tinqin.academy.api.models.update;

import com.tinqin.academy.api.operations.OperationInput;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class CarUpdateRequest implements OperationInput {
}
