package com.tinqin.academy.db.repositories;

import com.tinqin.academy.db.entities.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RaceRepository extends JpaRepository<Race,Long> {
    Race getRaceByCircuitName(String circuitName);
    Race getRaceByWinnerFirstName(String winnerName);
    Race getRaceByCircuitNameAndDate(String circuitName, Date date);
}
