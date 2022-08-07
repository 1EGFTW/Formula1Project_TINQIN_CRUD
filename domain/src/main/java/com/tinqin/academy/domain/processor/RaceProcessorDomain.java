package com.tinqin.academy.domain.processor;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.models.create.RaceCreateRequest;
import com.tinqin.academy.api.models.get.requests.RaceGetRequest;
import com.tinqin.academy.api.models.get.response.RaceGetResponse;
import com.tinqin.academy.api.models.update.RaceUpdateRequest;
import com.tinqin.academy.api.operation.RaceProcessor;
import com.tinqin.academy.db.service.interfaces.*;
import com.tinqin.academy.db.service.mapper.RaceResponseMapper;
import com.tinqin.academy.domain.error.GeneralServerError;
import com.tinqin.academy.domain.error.race.CouldntAddRaceError;
import com.tinqin.academy.domain.error.race.NoSuchRaceError;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RaceProcessorDomain implements RaceProcessor {
    private final ConversionService conversionService;
    private final DeleteService deleteService;
    private final AddService<RaceCreateRequest> addService;
    private final GetService<RaceResponseMapper> getService;
    private final FindService<RaceGetRequest,RaceResponseMapper> findService;
    private final UpdateService<RaceUpdateRequest> updateService;

    public RaceProcessorDomain(ConversionService conversionService, @Qualifier("race") DeleteService deleteService, AddService<RaceCreateRequest> addService, GetService<RaceResponseMapper> getService, FindService<RaceGetRequest, RaceResponseMapper> findService, UpdateService<RaceUpdateRequest> updateService) {
        this.conversionService = conversionService;
        this.deleteService = deleteService;
        this.addService = addService;
        this.getService = getService;
        this.findService = findService;
        this.updateService = updateService;
    }

    @Override
    public Either<Error, RaceGetResponse> processFind(RaceGetRequest circuitName) {
        return Try.of(()->{
            return conversionService.convert(findService.find(circuitName),RaceGetResponse.class);
        }).toEither()
                .mapLeft(throwable -> {
                   if(throwable instanceof NoSuchElementException)
                       return new NoSuchRaceError();
                   return new GeneralServerError();
                });
    }

    @Override
    public Either<Error, RaceGetResponse> processById(Long id) {
        return Try.of(()->{
                    return conversionService.convert(getService.getById(id),RaceGetResponse.class);
                }).toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new NoSuchRaceError();
                    return new GeneralServerError();
                });
    }

    @Override
    public Either<Error, Long> processAdd(RaceCreateRequest raceCreateRequest) {
        return Try.of(()->{
                   return addService.add(raceCreateRequest);
                }).toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new CouldntAddRaceError();
                    return new GeneralServerError();
                });
    }

    @Override
    public HttpStatus processDelete(Long id) {
        deleteService.delete(id);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus processUpdate(Long id, RaceUpdateRequest raceUpdateRequest) {
        updateService.update(id,raceUpdateRequest);
        return HttpStatus.OK;
    }
}
