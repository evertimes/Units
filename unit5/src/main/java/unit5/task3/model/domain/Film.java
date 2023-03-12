package unit5.task3.model.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Film implements Serializable {

    private String filmName;
    private Actor mainActor;
}
