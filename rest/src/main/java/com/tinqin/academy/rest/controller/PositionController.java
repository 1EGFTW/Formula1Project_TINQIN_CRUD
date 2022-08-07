package com.tinqin.academy.rest.controller;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.models.create.PositionCreateRequest;
import com.tinqin.academy.api.models.create.TeamCreateRequest;
import com.tinqin.academy.api.models.get.requests.PositionGetRequest;
import com.tinqin.academy.api.models.get.requests.TeamGetRequest;
import com.tinqin.academy.api.models.get.response.PositionGetResponse;
import com.tinqin.academy.api.models.get.response.TeamGetResponse;
import com.tinqin.academy.api.models.update.PositionUpdateRequest;
import com.tinqin.academy.api.models.update.TeamUpdateRequest;
import com.tinqin.academy.api.operation.PositionProcessor;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PositionController {
    private final PositionProcessor positionProcessor;

    public PositionController(PositionProcessor positionProcessor) {
        this.positionProcessor = positionProcessor;
    }
    @PostMapping("/position/add")
    public ResponseEntity<?> addPosition(@RequestBody PositionCreateRequest positionCreateRequest){
        Either<Error,Long> responses=positionProcessor.processAdd(positionCreateRequest);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Id of created position: "+responses.get());
    }

    @DeleteMapping("/position/delete/{id}")
    public HttpStatus deletePosition(@PathVariable Long id){
        return positionProcessor.processDelete(id);
    }

    @PutMapping("/position/update/{id}")
    public HttpStatus updatePosition(@RequestBody PositionUpdateRequest positionUpdateRequest, @PathVariable Long id){
        return positionProcessor.processUpdate(id,positionUpdateRequest);
    }

    @GetMapping("/position/find")
    public ResponseEntity<?> findPosition(@RequestParam String positionName){
        PositionGetRequest positionGetRequest= PositionGetRequest.builder()
                .positionName(positionName)
                .build();
        Either<Error, PositionGetResponse> responses=positionProcessor.processFind(positionGetRequest);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses.get());
    }

    @GetMapping("/position/{id}")
    public ResponseEntity<?> getPosition(@PathVariable Long id){
        Either<Error,PositionGetResponse> responses=positionProcessor.processById(id);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses.get());
    }
}
