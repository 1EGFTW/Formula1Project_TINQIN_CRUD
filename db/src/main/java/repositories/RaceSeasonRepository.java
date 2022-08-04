package repositories;

import entities.RaceSeason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceSeasonRepository extends JpaRepository<RaceSeason,Long> {
}
