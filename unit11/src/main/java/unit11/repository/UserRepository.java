package unit11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unit11.data.domain.UsersEntity;

public interface UserRepository extends JpaRepository<UsersEntity, Integer> {

}
