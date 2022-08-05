package repositories;

import entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends CrudRepository<Driver,Long> {
}
