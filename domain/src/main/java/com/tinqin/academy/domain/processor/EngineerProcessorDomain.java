package com.tinqin.academy.domain.processor;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.models.create.EngineerCreateRequest;
import com.tinqin.academy.api.models.get.requests.EngineerGetRequest;
import com.tinqin.academy.api.models.get.response.EngineerGetResponse;
import com.tinqin.academy.api.models.update.EngineerUpdateRequest;
import com.tinqin.academy.api.operation.EngineerProcessor;
import com.tinqin.academy.db.service.interfaces.*;
import com.tinqin.academy.db.service.mapper.EngineerResponseMapper;
import com.tinqin.academy.domain.error.engineer.CouldntAddEngineerError;
import com.tinqin.academy.domain.error.GeneralServerError;
import com.tinqin.academy.domain.error.engineer.NoSuchEngineerError;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EngineerProcessorDomain implements EngineerProcessor {
    private final ConversionService conversionService;
    private final DeleteService deleteService;
    private final AddService<EngineerCreateRequest> addService;
    private final GetService<EngineerResponseMapper> getService;
    private final FindService<EngineerGetRequest,EngineerResponseMapper> findService;
    private final UpdateService<EngineerUpdateRequest> updateService;

    public EngineerProcessorDomain(ConversionService conversionService, @Qualifier("engineer") DeleteService deleteService, AddService<EngineerCreateRequest> addService, GetService<EngineerResponseMapper> getService, FindService<EngineerGetRequest, EngineerResponseMapper> findService, UpdateService<EngineerUpdateRequest> updateService) {
        this.conversionService = conversionService;
        this.deleteService = deleteService;
        this.addService = addService;
        this.getService = getService;
        this.findService = findService;
        this.updateService = updateService;
    }

    @Override
    public Either<Error, EngineerGetResponse> processFind(EngineerGetRequest engineer) {
       return Try.of(()->{
           return conversionService.convert(findService.find(engineer),EngineerGetResponse.class);
       }).toEither()
               .mapLeft(throwable -> {
                   if(throwable instanceof NoSuchElementException)
                       return new NoSuchEngineerError();
                   return new GeneralServerError();
               });
    }

    @Override
    public Either<Error, EngineerGetResponse> processById(Long id) {
        return Try.of(()->{
            return conversionService.convert(getService.getById(id),EngineerGetResponse.class);
        }).toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new NoSuchEngineerError();
                    return new GeneralServerError();
                });
    }

    @Override
    public Either<Error, Long> processAdd(EngineerCreateRequest engineerCreateRequest) {
        return Try.of(()->{
            return addService.add(engineerCreateRequest);
        }).toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new CouldntAddEngineerError();
                    return new GeneralServerError();
                });
    }

    @Override
    public HttpStatus processDelete(Long id) {
        deleteService.delete(id);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus processUpdate(Long id, EngineerUpdateRequest engineerUpdateRequest) {
        updateService.update(id,engineerUpdateRequest);
        return HttpStatus.OK;
    }
}
