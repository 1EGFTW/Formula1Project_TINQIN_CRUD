package repositories;

import entities.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineerRepository extends CrudRepository<Engineer,Long> {
}
