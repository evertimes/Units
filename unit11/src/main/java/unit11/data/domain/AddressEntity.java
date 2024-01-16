package unit11.data.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "addresses")
@Where(clause = "DELETED = false")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {

    @Id
    private String address;
    @JsonIgnore
    private boolean deleted = false;
    @Version
    @JsonIgnore
    private int version;
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    @JsonBackReference
    @Exclude
    private Set<UsersEntity> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        AddressEntity that = (AddressEntity) o;
        return address != null && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
