package unit8.data.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsersEntity {

    @Id
    @SequenceGenerator(name = "users_sequence", sequenceName = "users_sequence", allocationSize = 1)
    @GeneratedValue(generator = "users_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity addressId;
    @ManyToMany
    @JoinTable(name = "users_cars",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private Set<CarsEntity> cars;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        UsersEntity that = (UsersEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, addressId);
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", addressId=" + addressId +
               '}';
    }
}
