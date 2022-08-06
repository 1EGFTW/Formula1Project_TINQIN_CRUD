package repositories;

import entities.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends CrudRepository<Season,Long> {
    Season getSeasonByYear(Integer year);
}
