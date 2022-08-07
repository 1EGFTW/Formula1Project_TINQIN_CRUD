package com.tinqin.academy.domain.processor;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.models.create.SeasonCreateRequest;
import com.tinqin.academy.api.models.get.requests.SeasonGetRequest;
import com.tinqin.academy.api.models.get.response.SeasonGetResponse;
import com.tinqin.academy.api.models.update.SeasonUpdateRequest;
import com.tinqin.academy.api.operation.SeasonProcessor;
import com.tinqin.academy.db.service.interfaces.*;
import com.tinqin.academy.db.service.mapper.SeasonResponseMapper;
import com.tinqin.academy.domain.error.GeneralServerError;
import com.tinqin.academy.domain.error.season.CouldntAddSeasonError;
import com.tinqin.academy.domain.error.season.NoSuchSeasonError;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SeasonProcessorDomain implements SeasonProcessor {
    private final ConversionService conversionService;
    private final DeleteService deleteService;
    private final AddService<SeasonCreateRequest> addService;
    private final GetService<SeasonResponseMapper> getService;
    private final FindService<SeasonGetRequest,SeasonResponseMapper> findService;
    private final UpdateService<SeasonUpdateRequest> updateService;

    public SeasonProcessorDomain(ConversionService conversionService, @Qualifier("season") DeleteService deleteService, AddService<SeasonCreateRequest> addService, GetService<SeasonResponseMapper> getService, FindService<SeasonGetRequest, SeasonResponseMapper> findService, UpdateService<SeasonUpdateRequest> updateService) {
        this.conversionService = conversionService;
        this.deleteService = deleteService;
        this.addService = addService;
        this.getService = getService;
        this.findService = findService;
        this.updateService = updateService;
    }

    @Override
    public Either<Error, SeasonGetResponse> processFind(SeasonGetRequest seasonName) {
        return Try.of(()->{
            return conversionService.convert(findService.find(seasonName),SeasonGetResponse.class);
        }).toEither()
                .mapLeft(throwable -> {
                   if(throwable instanceof NoSuchElementException)
                       return new NoSuchSeasonError();
                   return new GeneralServerError();
                });
    }

    @Override
    public Either<Error, SeasonGetResponse> processById(Long id) {
        return Try.of(()->{
                    return conversionService.convert(getService.getById(id),SeasonGetResponse.class);
                }).toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new NoSuchSeasonError();
                    return new GeneralServerError();
                });
    }

    @Override
    public Either<Error, Long> processAdd(SeasonCreateRequest seasonCreateRequest) {
        return Try.of(()->{
                    return addService.add(seasonCreateRequest);
                }).toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new CouldntAddSeasonError();
                    return new GeneralServerError();
                });
    }

    @Override
    public HttpStatus processDelete(Long id) {
        deleteService.delete(id);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus processUpdate(Long id, SeasonUpdateRequest seasonUpdateRequest) {
        updateService.update(id,seasonUpdateRequest);
        return HttpStatus.OK;
    }
}
