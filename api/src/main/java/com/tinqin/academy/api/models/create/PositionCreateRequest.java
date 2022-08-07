package com.tinqin.academy.api.models.create;

import com.tinqin.academy.api.base.OperationInput;
import lombok.*;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class PositionCreateRequest  implements OperationInput {
    private String positionName;
}
