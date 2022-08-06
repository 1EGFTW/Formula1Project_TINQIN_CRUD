package repositories;

import entities.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends CrudRepository<Race,Long> {
    Race getRaceByCircuitName(String circuitName);
    Race getRaceByWinnerFirstName(String winnerName);
}
