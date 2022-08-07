package com.tinqin.academy.api.operation;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.base.OperationProcessor;
import com.tinqin.academy.api.models.create.EngineerCreateRequest;
import com.tinqin.academy.api.models.create.PositionCreateRequest;
import com.tinqin.academy.api.models.get.requests.EngineerGetRequest;
import com.tinqin.academy.api.models.get.requests.PositionGetRequest;
import com.tinqin.academy.api.models.get.response.EngineerGetResponse;
import com.tinqin.academy.api.models.get.response.PositionGetResponse;
import com.tinqin.academy.api.models.update.EngineerUpdateRequest;
import com.tinqin.academy.api.models.update.PositionUpdateRequest;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;

public interface EngineerProcessor extends OperationProcessor<EngineerGetRequest, EngineerGetResponse> {
    @Override
    Either<Error, EngineerGetResponse> processFind(EngineerGetRequest engineer);
    @Override
    Either<Error,EngineerGetResponse> processById(Long id);

    Either<Error,Long> processAdd(EngineerCreateRequest engineerCreateRequest);
    @Override
    HttpStatus processDelete(Long id); // TBD

    HttpStatus processUpdate(Long id, EngineerUpdateRequest engineerUpdateRequest);
}
