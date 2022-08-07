package com.tinqin.academy.api.base;

import com.tinqin.academy.api.models.create.TeamCreateRequest;
import com.tinqin.academy.api.models.get.requests.TeamGetRequest;
import com.tinqin.academy.api.models.get.response.TeamGetResponse;
import com.tinqin.academy.api.models.update.TeamUpdateRequest;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;

public interface OperationProcessor< I extends OperationInput,R extends OperationResult> {
    Either<Error, R> processFind(I input);

    Either<Error,R> processById(Long id);

    HttpStatus processDelete(Long id); // TBD


}
