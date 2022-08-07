package com.tinqin.academy.domain.processor;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.models.create.DriverCreateRequest;
import com.tinqin.academy.api.models.get.requests.DriverGetRequest;
import com.tinqin.academy.api.models.get.response.DriverGetResponse;
import com.tinqin.academy.api.models.update.DriverUpdateRequest;
import com.tinqin.academy.api.operation.DriverProcessor;
import com.tinqin.academy.db.service.interfaces.*;
import com.tinqin.academy.db.service.mapper.DriverResponseMapper;
import com.tinqin.academy.domain.error.GeneralServerError;
import com.tinqin.academy.domain.error.driver.CouldntAddDriverError;
import com.tinqin.academy.domain.error.driver.NoSuchDriverError;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DriverProcessorDomain implements DriverProcessor {
    private final ConversionService conversionService;
    private final DeleteService deleteService;
    private final AddService<DriverCreateRequest> addService;
    private final FindService<DriverGetRequest, DriverResponseMapper> findService;
    private final GetService<DriverResponseMapper> getService;
    private final UpdateService<DriverUpdateRequest> updateService;

    public DriverProcessorDomain(ConversionService conversionService, @Qualifier("driver") DeleteService deleteService, AddService<DriverCreateRequest> addService, FindService<DriverGetRequest, DriverResponseMapper> findService, GetService<DriverResponseMapper> getService, UpdateService<DriverUpdateRequest> updateService) {
        this.conversionService = conversionService;
        this.deleteService = deleteService;
        this.addService = addService;
        this.findService = findService;
        this.getService = getService;
        this.updateService = updateService;
    }

    @Override
    public Either<Error, DriverGetResponse> processFind(DriverGetRequest Driver) {
        return Try.of(()->{
            return conversionService.convert(findService.find(Driver),DriverGetResponse.class);
        }).toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new NoSuchDriverError();
                    return new GeneralServerError();
                });
    }

    @Override
    public Either<Error, DriverGetResponse> processById(Long id) {
        return Try.of(()->{
                    return conversionService.convert(getService.getById(id),DriverGetResponse.class);
                }).toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new NoSuchDriverError();
                    return new GeneralServerError();
                });
    }

    @Override
    public Either<Error, Long> processAdd(DriverCreateRequest DriverCreateRequest) {
        return Try.of(()->addService.add(DriverCreateRequest))
                .toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new CouldntAddDriverError();
                    return new GeneralServerError();
                });
    }

    @Override
    public HttpStatus processDelete(Long id) {
        deleteService.delete(id);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus processUpdate(Long id, DriverUpdateRequest DriverUpdateRequest) {
        updateService.update(id,DriverUpdateRequest);
        return HttpStatus.OK;
    }
}
