package com.tinqin.academy.api.models.get.response;

import com.tinqin.academy.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class EngineerGetResponse implements OperationResult {
    private String firstName;

    private String lastName;

    private String team;

    private String position;

}
