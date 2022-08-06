package com.tinqin.academy.api.models.get.response;

import com.tinqin.academy.api.operations.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class PositionGetResponse implements OperationResult {
}
