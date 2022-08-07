package com.tinqin.academy.api.operation;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.base.OperationProcessor;
import com.tinqin.academy.api.models.create.CarCreateRequest;
import com.tinqin.academy.api.models.create.EngineerCreateRequest;
import com.tinqin.academy.api.models.get.requests.CarGetRequest;
import com.tinqin.academy.api.models.get.requests.EngineerGetRequest;
import com.tinqin.academy.api.models.get.response.CarGetResponse;
import com.tinqin.academy.api.models.get.response.EngineerGetResponse;
import com.tinqin.academy.api.models.update.CarUpdateRequest;
import com.tinqin.academy.api.models.update.EngineerUpdateRequest;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;

public interface CarProcessor extends OperationProcessor<CarGetRequest, CarGetResponse> {
    @Override
    Either<Error, CarGetResponse> processFind(CarGetRequest car);
    @Override
    Either<Error,CarGetResponse> processById(Long id);

    Either<Error,Long> processAdd(CarCreateRequest carCreateRequest);
    @Override
    HttpStatus processDelete(Long id); // TBD

    HttpStatus processUpdate(Long id, CarUpdateRequest carUpdateRequest);
}
