package com.tinqin.academy.rest.controller;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.models.create.TeamCreateRequest;
import com.tinqin.academy.api.models.get.requests.TeamGetRequest;
import com.tinqin.academy.api.models.get.response.TeamGetResponse;
import com.tinqin.academy.api.models.update.TeamUpdateRequest;
import com.tinqin.academy.api.operation.TeamProcessor;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/team")
public class TeamController {
    private final TeamProcessor teamProcessor;

    public TeamController(TeamProcessor teamProcessor) {
        this.teamProcessor = teamProcessor;
    }

    @PostMapping("/add")
    public ResponseEntity<?>addTeam(@RequestBody TeamCreateRequest teamCreateRequest){
        Either<Error,Long> responses=teamProcessor.processAdd(teamCreateRequest);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Id of created team: "+responses.get());
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteTeam(@PathVariable Long id){
        return teamProcessor.processDelete(id);
    }

    @PutMapping("/update/{id}")
    public HttpStatus updateTeam(@RequestBody TeamUpdateRequest teamUpdateRequest,@PathVariable Long id){
        return teamProcessor.processUpdate(id,teamUpdateRequest);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findPlace(@RequestParam String teamName){
       TeamGetRequest teamGetRequest= TeamGetRequest.builder()
               .teamName(teamName)
               .build();
       Either<Error,TeamGetResponse> responses=teamProcessor.process(teamGetRequest);
       if(responses.isLeft()){
           return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
       }
       return ResponseEntity.status(HttpStatus.OK).body(responses.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlace(@PathVariable Long id){
        Either<Error,TeamGetResponse> responses=teamProcessor.processById(id);
        if(responses.isLeft()){
            return ResponseEntity.status(responses.getLeft().getCode()).body(responses.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses.get());
    }


}
