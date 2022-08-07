package com.tinqin.academy.rest.controller;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.models.create.SeasonCreateRequest;
import com.tinqin.academy.api.models.get.requests.SeasonGetRequest;
import com.tinqin.academy.api.models.get.response.SeasonGetResponse;
import com.tinqin.academy.api.models.update.SeasonUpdateRequest;
import com.tinqin.academy.api.operation.SeasonProcessor;
import com.tinqin.academy.api.operation.SeasonProcessor;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SeasonController {
    private final SeasonProcessor seasonProcessor;

    public SeasonController(SeasonProcessor seasonProcessor) {
        this.seasonProcessor = seasonProcessor;
    }

    @PostMapping("/Season/add")
    public ResponseEntity<?> addSeason(@RequestBody SeasonCreateRequest seasonCreateRequest){
        Either<Error,Long> responses=seasonProcessor.processAdd(seasonCreateRequest);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Id of created Season: "+responses.get());
    }

    @DeleteMapping("/season/delete/{id}")
    public HttpStatus deleteSeason(@PathVariable Long id){
        return seasonProcessor.processDelete(id);
    }

    @PutMapping("/season/update/{id}")
    public HttpStatus updateSeason(@RequestBody SeasonUpdateRequest seasonUpdateRequest, @PathVariable Long id){
        return seasonProcessor.processUpdate(id,seasonUpdateRequest);
    }

    @GetMapping("/season/find")
    public ResponseEntity<?> findSeason(@RequestParam Integer year){
        SeasonGetRequest seasonGetRequest= SeasonGetRequest.builder()
                .seasonYear(year)
                .build();
        Either<Error, SeasonGetResponse> responses=seasonProcessor.processFind(seasonGetRequest);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses.get());
    }

    @GetMapping("/season/{id}")
    public ResponseEntity<?> getSeason(@PathVariable Long id){
        Either<Error,SeasonGetResponse> responses=seasonProcessor.processById(id);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses.get());
    }
}
