package unit8.data.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

@Table(name = "cars")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarsEntity {

    @Id
    @SequenceGenerator(name = "cars_sequence", sequenceName = "cars_sequence", allocationSize = 1)
    @GeneratedValue(generator = "cars_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String number;
    private String brand;
    @ManyToMany
    @JoinTable(name = "users_cars",
        joinColumns = @JoinColumn(name = "car_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UsersEntity> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        CarsEntity that = (CarsEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, brand);
    }

    @Override
    public String toString() {
        return "CarsEntity{" +
               "id=" + id +
               ", number='" + number + '\'' +
               ", brand='" + brand + '\'' +
               '}';
    }
}
