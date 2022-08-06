package com.tinqin.academy.api.models.get.response;

import com.tinqin.academy.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class CarGetResponse  implements OperationResult {
    private String modelName;

    private Integer horsepower;

    private Integer torque;
}
