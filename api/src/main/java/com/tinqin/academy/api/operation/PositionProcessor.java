package com.tinqin.academy.api.operation;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.base.OperationProcessor;
import com.tinqin.academy.api.models.create.PositionCreateRequest;
import com.tinqin.academy.api.models.create.TeamCreateRequest;
import com.tinqin.academy.api.models.get.requests.PositionGetRequest;
import com.tinqin.academy.api.models.get.requests.TeamGetRequest;
import com.tinqin.academy.api.models.get.response.PositionGetResponse;
import com.tinqin.academy.api.models.get.response.TeamGetResponse;
import com.tinqin.academy.api.models.update.PositionUpdateRequest;
import com.tinqin.academy.api.models.update.TeamUpdateRequest;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;

public interface PositionProcessor extends OperationProcessor<PositionGetRequest, PositionGetResponse> {
    @Override
    Either<Error, PositionGetResponse> processFind(PositionGetRequest positionName);
    @Override
    Either<Error,PositionGetResponse> processById(Long id);

    Either<Error,Long> processAdd(PositionCreateRequest positionCreateRequest);
    @Override
    HttpStatus processDelete(Long id); // TBD

    HttpStatus processUpdate(Long id, PositionUpdateRequest positionUpdateRequest);
}
