package unit5.task3.model.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Actor implements Serializable {

    private static final long serialVersionUID = 6715389338554508363L;

    private String name;
}
