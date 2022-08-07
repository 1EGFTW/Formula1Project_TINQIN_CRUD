package com.tinqin.academy.domain.processor;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.models.create.PositionCreateRequest;
import com.tinqin.academy.api.models.get.requests.PositionGetRequest;
import com.tinqin.academy.api.models.get.response.PositionGetResponse;
import com.tinqin.academy.api.models.update.PositionUpdateRequest;
import com.tinqin.academy.api.operation.PositionProcessor;
import com.tinqin.academy.db.service.interfaces.*;
import com.tinqin.academy.db.service.mapper.PositionResponseMapper;
import com.tinqin.academy.domain.error.position.CouldntAddPositionError;
import com.tinqin.academy.domain.error.GeneralServerError;
import com.tinqin.academy.domain.error.position.NoSuchPositionError;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PositionProcessorDomain implements PositionProcessor {
    private final ConversionService conversionService;
    private final AddService<PositionCreateRequest> addService;
    private final GetService<PositionResponseMapper> getService;
    private final FindService<PositionGetRequest,PositionResponseMapper> findService;
    private final DeleteService deleteService;
    private final UpdateService<PositionUpdateRequest> updateService;

    public PositionProcessorDomain(ConversionService conversionService, AddService<PositionCreateRequest> addService, GetService<PositionResponseMapper> getService, FindService<PositionGetRequest, PositionResponseMapper> findService, @Qualifier("position") DeleteService deleteService, UpdateService<PositionUpdateRequest> updateService) {
        this.conversionService = conversionService;
        this.addService = addService;
        this.getService = getService;
        this.findService = findService;
        this.deleteService = deleteService;
        this.updateService = updateService;
    }

    @Override
    public Either<Error, PositionGetResponse> processFind(PositionGetRequest positionName) {
        return Try.of(()->{
                    return conversionService.convert(findService.find(positionName), PositionGetResponse.class);
                }).toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new NoSuchPositionError();
                    return new GeneralServerError();
                });
    }

    @Override
    public Either<Error, PositionGetResponse> processById(Long id) {
        return Try.of(()->{
                    return conversionService.convert(getService.getById(id), PositionGetResponse.class);
                }).toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new NoSuchPositionError();
                    return new GeneralServerError();
                });
    }

    @Override
    public Either<Error, Long> processAdd(PositionCreateRequest positionCreateRequest) {
        return Try.of(()-> addService.add(positionCreateRequest))
                .toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new CouldntAddPositionError();
                    return new GeneralServerError();
                });
    }

    @Override
    public HttpStatus processDelete(Long id) {
        deleteService.delete(id);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus processUpdate(Long id, PositionUpdateRequest positionUpdateRequest) {
        updateService.update(id,positionUpdateRequest);
        return HttpStatus.OK;
    }
}
