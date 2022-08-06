package com.tinqin.academy.api.models.update;

import com.tinqin.academy.api.base.OperationInput;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class DriverUpdateRequest implements OperationInput {
    private Integer points;
    private String driverType;
    private Integer age;
    private String teamName;
    private String carModel;
}
