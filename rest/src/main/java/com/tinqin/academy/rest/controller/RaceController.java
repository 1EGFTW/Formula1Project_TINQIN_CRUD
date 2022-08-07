package com.tinqin.academy.rest.controller;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.models.create.RaceCreateRequest;
import com.tinqin.academy.api.models.get.requests.RaceGetRequest;
import com.tinqin.academy.api.models.get.requests.SeasonGetRequest;
import com.tinqin.academy.api.models.get.response.RaceGetResponse;
import com.tinqin.academy.api.models.update.RaceUpdateRequest;
import com.tinqin.academy.api.operation.RaceProcessor;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RaceController {
    private final RaceProcessor raceProcessor;

    public RaceController(RaceProcessor raceProcessor) {
        this.raceProcessor = raceProcessor;
    }
    @PostMapping("/race/add")
    public ResponseEntity<?> addRace(@RequestBody RaceCreateRequest raceCreateRequest){
        Either<Error,Long> responses=raceProcessor.processAdd(raceCreateRequest);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Id of created race: "+responses.get());
    }

    @DeleteMapping("/race/delete/{id}")
    public HttpStatus deleteRace(@PathVariable Long id){
        return raceProcessor.processDelete(id);
    }

    @PutMapping("/race/update/{id}")
    public HttpStatus updateRace(@RequestBody RaceUpdateRequest raceUpdateRequest, @PathVariable Long id){
        return raceProcessor.processUpdate(id,raceUpdateRequest);
    }

    @GetMapping("/race/find")
    public ResponseEntity<?> findRace(@RequestParam Integer year,@RequestParam String circuitName){
        SeasonGetRequest seasonGetRequest= SeasonGetRequest.builder()
                .seasonYear(year)
                .build();
        RaceGetRequest raceGetRequest= RaceGetRequest.builder()
                .circuitName(circuitName)
                .seasons(seasonGetRequest)
                .build();
        Either<Error, RaceGetResponse> responses=raceProcessor.processFind(raceGetRequest);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses.get());
    }

    @GetMapping("/race/{id}")
    public ResponseEntity<?> getRace(@PathVariable Long id){
        Either<Error,RaceGetResponse> responses=raceProcessor.processById(id);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses.get());
    }
}
