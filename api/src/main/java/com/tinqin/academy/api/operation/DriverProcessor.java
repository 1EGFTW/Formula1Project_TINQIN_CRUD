package com.tinqin.academy.api.operation;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.base.OperationProcessor;
import com.tinqin.academy.api.models.create.DriverCreateRequest;
import com.tinqin.academy.api.models.get.requests.DriverGetRequest;
import com.tinqin.academy.api.models.get.requests.DriverGetRequest;
import com.tinqin.academy.api.models.get.response.DriverGetResponse;
import com.tinqin.academy.api.models.get.response.DriverGetResponse;
import com.tinqin.academy.api.models.update.DriverUpdateRequest;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;

public interface DriverProcessor extends OperationProcessor<DriverGetRequest, DriverGetResponse> {
    @Override
    Either<Error, DriverGetResponse> processFind(DriverGetRequest Driver);
    @Override
    Either<Error,DriverGetResponse> processById(Long id);

    Either<Error,Long> processAdd(DriverCreateRequest DriverCreateRequest);
    @Override
    HttpStatus processDelete(Long id); // TBD

    HttpStatus processUpdate(Long id, DriverUpdateRequest DriverUpdateRequest);
}
