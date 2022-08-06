package com.tinqin.academy.api.operation;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.base.OperationProcessor;
import com.tinqin.academy.api.models.create.TeamCreateRequest;
import com.tinqin.academy.api.models.get.requests.TeamGetRequest;
import com.tinqin.academy.api.models.get.response.TeamGetResponse;
import com.tinqin.academy.api.models.update.TeamUpdateRequest;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface TeamProcessor extends OperationProcessor<TeamGetRequest, TeamGetResponse> {
    @Override
    Either<Error, TeamGetResponse> process(TeamGetRequest teamGetRequest);

    Either<Error,TeamGetResponse> processById(Long id);

    Either<Error,Long> processAdd(TeamCreateRequest teamCreateRequest);

    HttpStatus processDelete(Long id); // TBD

    HttpStatus processUpdate(Long id,TeamUpdateRequest teamUpdateRequest);
}
