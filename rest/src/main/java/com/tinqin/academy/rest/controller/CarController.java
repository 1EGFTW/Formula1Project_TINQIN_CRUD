package com.tinqin.academy.rest.controller;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.models.create.CarCreateRequest;
import com.tinqin.academy.api.models.get.requests.CarGetRequest;
import com.tinqin.academy.api.models.get.response.CarGetResponse;
import com.tinqin.academy.api.models.update.CarUpdateRequest;
import com.tinqin.academy.api.operation.CarProcessor;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {
    private final CarProcessor carProcessor;

    public CarController(CarProcessor carProcessor) {
        this.carProcessor = carProcessor;
    }

    @PostMapping("/car/add")
    public ResponseEntity<?> addCar(@RequestBody CarCreateRequest carCreateRequest){
        Either<Error,Long> responses=carProcessor.processAdd(carCreateRequest);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Id of created car: "+responses.get());
    }

    @DeleteMapping("/car/delete/{id}")
    public HttpStatus deleteCar(@PathVariable Long id){
        return carProcessor.processDelete(id);
    }

    @PutMapping("/car/update/{id}")
    public HttpStatus updateCar(@RequestBody CarUpdateRequest carUpdateRequest, @PathVariable Long id){
        return carProcessor.processUpdate(id,carUpdateRequest);
    }

    @GetMapping("/car/find")
    public ResponseEntity<?> findCar(@RequestParam String carName){
        CarGetRequest carGetRequest= CarGetRequest.builder()
                .modelName(carName)
                .build();
        Either<Error, CarGetResponse> responses=carProcessor.processFind(carGetRequest);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses.get());
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<?> getCar(@PathVariable Long id){
        Either<Error, CarGetResponse> responses=carProcessor.processById(id);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses.get());
    }
}
