package unit11.data.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "addresses")
@Where(clause = "DELETED = false")
@Data
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
    private Set<UsersEntity> users;

}
