package com.tinqin.academy.api.operation;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.base.OperationProcessor;
import com.tinqin.academy.api.models.create.RaceCreateRequest;
import com.tinqin.academy.api.models.get.requests.RaceGetRequest;
import com.tinqin.academy.api.models.get.requests.RaceGetRequest;
import com.tinqin.academy.api.models.get.response.RaceGetResponse;
import com.tinqin.academy.api.models.get.response.RaceGetResponse;
import com.tinqin.academy.api.models.update.RaceUpdateRequest;
import io.vavr.control.Either;
import jdk.dynalink.Operation;
import org.springframework.http.HttpStatus;

public interface RaceProcessor extends OperationProcessor<RaceGetRequest, RaceGetResponse> {
    @Override
    Either<Error, RaceGetResponse> processFind(RaceGetRequest circuitName);
    @Override
    Either<Error,RaceGetResponse> processById(Long id);

    Either<Error,Long> processAdd(RaceCreateRequest raceCreateRequest);
    @Override
    HttpStatus processDelete(Long id); // TBD

    HttpStatus processUpdate(Long id, RaceUpdateRequest raceUpdateRequest);
}
