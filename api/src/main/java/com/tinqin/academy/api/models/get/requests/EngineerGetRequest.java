package com.tinqin.academy.api.models.get.requests;

import com.tinqin.academy.api.base.OperationInput;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class EngineerGetRequest  implements OperationInput {
    private String engineerName;
}
