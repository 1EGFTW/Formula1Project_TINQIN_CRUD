package com.tinqin.academy.domain.processor;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.models.create.CarCreateRequest;
import com.tinqin.academy.api.models.get.requests.CarGetRequest;
import com.tinqin.academy.api.models.get.response.CarGetResponse;
import com.tinqin.academy.api.models.update.CarUpdateRequest;
import com.tinqin.academy.api.operation.CarProcessor;
import com.tinqin.academy.db.service.interfaces.*;
import com.tinqin.academy.db.service.mapper.CarResponseMapper;
import com.tinqin.academy.domain.error.GeneralServerError;
import com.tinqin.academy.domain.error.car.CouldntAddCarError;
import com.tinqin.academy.domain.error.car.NoSuchCarError;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CarProcessorDomain implements CarProcessor {
    private final ConversionService conversionService;
    private final DeleteService deleteService;
    private final AddService<CarCreateRequest> addService;
    private final GetService<CarResponseMapper> getService;
    private final FindService<CarGetRequest,CarResponseMapper> findService;
    private final UpdateService<CarUpdateRequest> updateService;

    public CarProcessorDomain(ConversionService conversionService, @Qualifier("car") DeleteService deleteService, AddService<CarCreateRequest> addService, GetService<CarResponseMapper> getService, FindService<CarGetRequest, CarResponseMapper> findService, UpdateService<CarUpdateRequest> updateService) {
        this.conversionService = conversionService;
        this.deleteService = deleteService;
        this.addService = addService;
        this.getService = getService;
        this.findService = findService;
        this.updateService = updateService;
    }

    @Override
    public Either<Error, CarGetResponse> processFind(CarGetRequest car) {
        return Try.of(()->{
            return conversionService.convert(findService.find(car),CarGetResponse.class);
        }).toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new NoSuchCarError();
                    return new GeneralServerError();
                });
    }

    @Override
    public Either<Error, CarGetResponse> processById(Long id) {
        return Try.of(()->conversionService.convert(getService.getById(id),CarGetResponse.class))
                .toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new NoSuchCarError();
                    return new GeneralServerError();
                });
    }

    @Override
    public Either<Error, Long> processAdd(CarCreateRequest carCreateRequest) {
        return Try.of(()->addService.add(carCreateRequest))
                .toEither()
                .mapLeft(throwable -> {
                   if(throwable instanceof NoSuchElementException)
                       return new CouldntAddCarError();
                   return new GeneralServerError();
                });
    }

    @Override
    public HttpStatus processDelete(Long id) {
        deleteService.delete(id);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus processUpdate(Long id, CarUpdateRequest carUpdateRequest) {
       updateService.update(id,carUpdateRequest);
        return HttpStatus.OK;
    }
}
