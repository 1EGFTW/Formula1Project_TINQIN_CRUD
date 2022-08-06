package com.tinqin.academy.db.repositories;

import com.tinqin.academy.db.entities.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season,Long> {
    Season getSeasonByYear(Integer year);
}
