package com.tinqin.academy.api.operation;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.base.OperationProcessor;
import com.tinqin.academy.api.models.create.SeasonCreateRequest;
import com.tinqin.academy.api.models.get.requests.SeasonGetRequest;
import com.tinqin.academy.api.models.get.requests.SeasonGetRequest;
import com.tinqin.academy.api.models.get.response.SeasonGetResponse;
import com.tinqin.academy.api.models.get.response.SeasonGetResponse;
import com.tinqin.academy.api.models.update.SeasonUpdateRequest;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;

public interface SeasonProcessor extends OperationProcessor<SeasonGetRequest, SeasonGetResponse> {
    @Override
    Either<Error, SeasonGetResponse> processFind(SeasonGetRequest seasonName);
    @Override
    Either<Error,SeasonGetResponse> processById(Long id);

    Either<Error,Long> processAdd(SeasonCreateRequest seasonCreateRequest);
    @Override
    HttpStatus processDelete(Long id); // TBD

    HttpStatus processUpdate(Long id, SeasonUpdateRequest seasonUpdateRequest);
}
