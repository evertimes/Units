package unit11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unit11.data.domain.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, String> {
}
