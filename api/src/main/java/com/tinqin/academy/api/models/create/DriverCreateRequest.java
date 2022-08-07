package com.tinqin.academy.api.models.create;

import com.tinqin.academy.api.base.OperationInput;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class DriverCreateRequest  implements OperationInput {
    private String firstName;

    private String lastName;

    private Integer age;

    private Integer driverPoints;

    private String driverType;
    private Integer championships;

    private String teamName;

    private String carModel;
}
