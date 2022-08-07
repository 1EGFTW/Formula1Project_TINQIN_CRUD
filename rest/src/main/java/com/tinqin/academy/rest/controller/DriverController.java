package com.tinqin.academy.rest.controller;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.models.create.DriverCreateRequest;

import com.tinqin.academy.api.models.get.requests.DriverGetRequest;

import com.tinqin.academy.api.models.get.response.DriverGetResponse;
import com.tinqin.academy.api.models.update.DriverUpdateRequest;

import com.tinqin.academy.api.operation.DriverProcessor;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DriverController {
    private final DriverProcessor driverProcessor;

    public DriverController(DriverProcessor driverProcessor) {
        this.driverProcessor = driverProcessor;
    }

    @PostMapping("/driver/add")
    public ResponseEntity<?> addDriver(@RequestBody DriverCreateRequest driverCreateRequest){
        Either<Error,Long> responses=driverProcessor.processAdd(driverCreateRequest);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Id of created driver: "+responses.get());
    }

    @DeleteMapping("/driver/delete/{id}")
    public HttpStatus deleteDriver(@PathVariable Long id){
        return driverProcessor.processDelete(id);
    }

    @PutMapping("/driver/update/{id}")
    public HttpStatus updateDriver(@RequestBody DriverUpdateRequest driverUpdateRequest, @PathVariable Long id){
        return driverProcessor.processUpdate(id,driverUpdateRequest);
    }

    @GetMapping("/driver/find")
    public ResponseEntity<?> findDriver(@RequestParam String firstName,@RequestParam String lastName){
        DriverGetRequest driverGetRequest= DriverGetRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        Either<Error, DriverGetResponse> responses=driverProcessor.processFind(driverGetRequest);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses.get());
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<?> getDriver(@PathVariable Long id){
        Either<Error,DriverGetResponse> responses=driverProcessor.processById(id);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses.get());
    }
}
