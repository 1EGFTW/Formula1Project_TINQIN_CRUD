package com.tinqin.academy.db.repositories;

import com.tinqin.academy.db.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {
    Position getPositionByPositionName(String positionName);
}
