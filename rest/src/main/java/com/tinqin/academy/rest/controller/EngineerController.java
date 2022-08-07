package com.tinqin.academy.rest.controller;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.models.create.EngineerCreateRequest;
import com.tinqin.academy.api.models.create.PositionCreateRequest;
import com.tinqin.academy.api.models.get.requests.EngineerGetRequest;
import com.tinqin.academy.api.models.get.requests.PositionGetRequest;
import com.tinqin.academy.api.models.get.response.EngineerGetResponse;
import com.tinqin.academy.api.models.get.response.PositionGetResponse;
import com.tinqin.academy.api.models.update.EngineerUpdateRequest;
import com.tinqin.academy.api.models.update.PositionUpdateRequest;
import com.tinqin.academy.api.operation.EngineerProcessor;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EngineerController {
    private final EngineerProcessor engineerProcessor;

    public EngineerController(EngineerProcessor engineerProcessor) {
        this.engineerProcessor = engineerProcessor;
    }
    @PostMapping("/engineer/add")
    public ResponseEntity<?> addEngineer(@RequestBody EngineerCreateRequest engineerCreateRequest){
        Either<Error,Long> responses=engineerProcessor.processAdd(engineerCreateRequest);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Id of created engineer: "+responses.get());
    }

    @DeleteMapping("/engineer/delete/{id}")
    public HttpStatus deleteEngineer(@PathVariable Long id){
        return engineerProcessor.processDelete(id);
    }

    @PutMapping("/engineer/update/{id}")
    public HttpStatus updateEngineer(@RequestBody EngineerUpdateRequest engineerUpdateRequest, @PathVariable Long id){
        return engineerProcessor.processUpdate(id,engineerUpdateRequest);
    }

    @GetMapping("/engineer/find")
    public ResponseEntity<?> findEngineer(@RequestParam String engineerName){
        EngineerGetRequest engineerGetRequest= EngineerGetRequest.builder()
                .engineerName(engineerName)
                .build();
        Either<Error, EngineerGetResponse> responses=engineerProcessor.processFind(engineerGetRequest);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses.get());
    }

    @GetMapping("/engineer/{id}")
    public ResponseEntity<?> getEngineer(@PathVariable Long id){
        Either<Error,EngineerGetResponse> responses=engineerProcessor.processById(id);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses.get());
    }
}
