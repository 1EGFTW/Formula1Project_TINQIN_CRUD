package com.tinqin.academy.db.repositories;

import com.tinqin.academy.db.entities.Engineer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineerRepository extends CrudRepository<Engineer,Long> {
    Engineer getEngineerByFirstName(String firstName);
}
