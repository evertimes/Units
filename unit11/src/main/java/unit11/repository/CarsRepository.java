package unit11.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import unit11.data.domain.CarsEntity;

public interface CarsRepository extends JpaRepository<CarsEntity, Integer> {

    Set<CarsEntity> findByUsers_Id(Integer name);

}
